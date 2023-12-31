package com.ycb.service.impl;

import com.ycb.common.constant.*;
import com.ycb.common.utils.EmailUtils;
import com.ycb.pojo.entity.Account;
import com.ycb.pojo.dto.RegisterDTO;
import com.ycb.pojo.dto.ResetPwDTO;
import com.ycb.exception.*;
import com.ycb.mapper.AccountMapper;
import com.ycb.service.AuthService;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 账户认证的服务实现类
 */
@Service
public class AuthServiceImpl implements AuthService {
    @Resource
    private AccountMapper accountMapper;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private JavaMailSender javaMailSender;
    @Resource
    private BCryptPasswordEncoder encoder;
    @Resource
    private EmailUtils emailUtils;

    /**
     * 根据用户名获取用户信息
     *
     * @param username 标识需要其数据的用户的用户名。
     * @return 完全填充的用户记录
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (accountMapper.isDisableByUsername(username) == StatusConstant.DISABLE) {
            throw new DisabledException("用户已被禁用");
        }
        Account account = accountMapper.getByUsername(username);
        // 没查到
        if (Objects.isNull(account)) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        return User.withUsername(username)
                .password(account.getPassword())
                .authorities(account.getAuthority())
                .build();
    }

    /**
     * 发送邮件
     */
    @Override
    public void sendEmail(String email, String type, String ip) {
        // 频繁请求
        if (frequent(ip)) {
            throw new RequestFrequentException();
        }
        // 删除已经生成验证码
        String key = RedisConstant.RECEIVE_MAIL + email;
        this.removeRKey(key);
        // 生成验证码
        Random random = new Random();
        int code = random.nextInt(899999) + 100000;
        SimpleMailMessage mailMessage = switch (type) {
            case EmailTypeConstant.REGISTER ->
                    emailUtils.createMailMessage(code + "是你的注册验证码", "你好！在创建帐号之前，你需要一个简单的步骤，让我们确保这是正确的邮件地址————\n\n请输入此验证码以开始使用：" + code + "\n验证码有效时间3分钟。如非本人操作可忽略。", email);
            case EmailTypeConstant.RESET_PASSWORD ->
                    emailUtils.createMailMessage(code + "是你的重置密码验证码", "亲爱的用户：\n注意！你正在进行重置密码操作！\n请输入此验证码以重置密码：" + code + "，验证码有效时间3分钟。\n\n提供给他人会导致账户被盗和资产损失，如非本人操作，请尽快修改密码。\n如果是您本人操作，请忽略本次提醒。", email);
            default -> throw new IllegalStateException(MessageConstant.NOT_EXIST_TYPE + type);
        };
        try {
            javaMailSender.send(mailMessage);
        } catch (MailException e) {
            throw new EmailException(HttpStatus.INTERNAL_SERVER_ERROR.value(), MessageConstant.EMAIL_SEND_FAILED);
        }
        // 邮箱、验证码存入redis，有效时间3分钟
        stringRedisTemplate.opsForValue()
                .set(key, String.valueOf(code), RedisConstant.VERIFICATION_CODE_EXPIRE_TIME, TimeUnit.MINUTES);
    }

    /**
     * 注册
     *
     * @param registerDTO 前端请求信息：邮箱、验证码、用户名、密码
     */
    @Override
    public void register(RegisterDTO registerDTO) {
        String key = RedisConstant.RECEIVE_MAIL + registerDTO.getEmail();
        // 验证验证码
        this.codeVerify(key, registerDTO.getCode());
        // 用户名已存在
        Account byUsername = accountMapper.getByUsername(registerDTO.getUsername());
        if (!Objects.isNull(byUsername)) {
            throw new RegisterException(HttpStatus.BAD_REQUEST.value(), MessageConstant.USERNAME_ALREADY_EXISTS);
        }
        // 邮箱已存在
        Account byEmail = accountMapper.getByEmail(registerDTO.getEmail());
        if (!Objects.isNull(byEmail)) {
            throw new RegisterException(HttpStatus.BAD_REQUEST.value(), MessageConstant.EMAIL_ALREADY_EXISTS);
        }
        Account account = new Account();
        account.setUsername(registerDTO.getUsername());
        account.setPassword(encoder.encode(registerDTO.getPassword()));
        account.setEmail(registerDTO.getEmail());
        account.setNikeName(registerDTO.getUsername());
        // 默认权限
        account.setAuthority(AuthorityConstant.DEFAULT_AUTHORITY);
        int len = accountMapper.save(account);
        if (len < 1) {
            throw new SystemException();
        }
        // 删除redis中的验证码数据
        this.removeRKey(key);
    }

    /**
     * 重置密码
     *
     * @param resetPwDTO 前端请求信息：邮箱、验证码、新密码
     */
    @Override
    public void resetPw(ResetPwDTO resetPwDTO) {
        // 验证验证码
        String key = RedisConstant.RECEIVE_MAIL + resetPwDTO.getEmail();
        this.codeVerify(key, resetPwDTO.getCode());
        // 还未注册
        Account byEmail = accountMapper.getByEmail(resetPwDTO.getEmail());
        if (Objects.isNull(byEmail)) {
            throw new ResetPasswordException(HttpStatus.BAD_REQUEST.value(), MessageConstant.ACCOUNT_NOT_FOUND);
        }
        // 新旧密码一样
        if (encoder.matches(resetPwDTO.getPassword(), byEmail.getPassword())) {
            throw new ResetPasswordException(HttpStatus.BAD_REQUEST.value(), MessageConstant.NEW_PASSWORD_SAME_AS_OLD_PASSWORD);
        }
        // 更新密码
        resetPwDTO.setPassword(encoder.encode(resetPwDTO.getPassword()));
        int len = accountMapper.updatePwByEmail(resetPwDTO);
        if (len < 1) {
            throw new SystemException();
        }
        // 删除redis中的验证码数据
        this.removeRKey(key);
    }

    /**
     * 验证验证码
     */
    private void codeVerify(String codeKey, String code) {
        // 未获取验证码
        if (Boolean.FALSE.equals(stringRedisTemplate.hasKey(codeKey))) {
            throw new EmailException(HttpStatus.BAD_REQUEST.value(), MessageConstant.GET_VERIFICATION_CODE_FIRST);
        }
        String rightCode = stringRedisTemplate.opsForValue().get(codeKey);
        if (rightCode == null) {
            throw new EmailException(HttpStatus.BAD_REQUEST.value(), MessageConstant.GET_VERIFICATION_CODE_FIRST);
        }
        // 验证码错误
        if (!rightCode.equals(code)) {
            throw new VerificationCodeException(HttpStatus.BAD_REQUEST.value(), MessageConstant.VERIFICATION_CODE_ERROR);
        }
    }

    /**
     * 如果redis中有key，就删除
     *
     * @param key key
     */
    private void removeRKey(String key) {
        if (Boolean.TRUE.equals(stringRedisTemplate.hasKey(key))) {
            stringRedisTemplate.delete(key);
        }
    }

    /**
     * 是否频繁请求
     */
    private boolean frequent(String ip) {
        String key = RedisConstant.SEND_MAIL_FREQUENT + ip;
        if (Boolean.TRUE.equals(stringRedisTemplate.hasKey(key))) {
            return true;
        } else {
            stringRedisTemplate.opsForValue().set(key, "", RedisConstant.FREQUENT_REQUEST_EXPIRE_TIME, TimeUnit.MINUTES);
        }
        return false;
    }
}

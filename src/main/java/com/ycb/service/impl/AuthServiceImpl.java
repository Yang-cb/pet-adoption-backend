package com.ycb.service.impl;

import com.ycb.constant.DefaultConstant;
import com.ycb.constant.MessageConstant;
import com.ycb.constant.TypeConstant;
import com.ycb.entity.dto.Account;
import com.ycb.entity.vo.request.RegisterVO;
import com.ycb.constant.RedisConstant;
import com.ycb.entity.vo.request.ResetPwVO;
import com.ycb.exception.*;
import com.ycb.mapper.AccountMapper;
import com.ycb.service.AuthService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 账户认证的服务实现类
 */
@Service
public class AuthServiceImpl implements AuthService {
    // 发件人
    @Value("${spring.mail.username}")
    private String fromEmail;

    @Resource
    private AccountMapper accountMapper;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private JavaMailSender javaMailSender;
    @Resource
    private BCryptPasswordEncoder encoder;

    /**
     * 根据用户名获取用户信息
     *
     * @param username 标识需要其数据的用户的用户名。
     * @return 完全填充的用户记录
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
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
            throw new RequestFrequentException(HttpStatus.TOO_MANY_REQUESTS.value(), MessageConstant.REQUEST_FREQUENT);
        }
        // 删除已经生成验证码
        String key = RedisConstant.RECEIVE_MAIL + email;
        this.removeRKey(key);
        // 生成验证码
        Random random = new Random();
        int code = random.nextInt(899999) + 100000;
        SimpleMailMessage mailMessage = switch (type) {
            case TypeConstant.SEND_MAIL_REGISTER ->
                    this.createMailMessage(code + "是你的注册验证码", "你好！在创建帐号之前，你需要一个简单的步骤，让我们确保这是正确的邮件地址————\n\n请输入此验证码以开始使用：" + code + "\n验证码有效时间3分钟。如非本人操作可忽略。", email);
            case TypeConstant.SEND_MAIL_RESET_PASSWORD ->
                    this.createMailMessage(code + "是你的重置密码验证码", "亲爱的用户：\n注意！你正在进行重置密码操作！\n请输入此验证码以重置密码：" + code + "，验证码有效时间3分钟。\n\n提供给他人会导致账户被盗和资产损失，如非本人操作，请尽快修改密码。\n如果是您本人操作，请忽略本次提醒。", email);
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
        try {
            javaMailSender.send(mailMessage);
        } catch (MailException e) {
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR.value(), MessageConstant.EMAIL_SEND_FAILED);
        }
        // 邮箱、验证码存入redis，有效时间3分钟
        stringRedisTemplate.opsForValue()
                .set(key, String.valueOf(code), RedisConstant.VERIFICATION_CODE_EXPIRE_TIME, TimeUnit.MINUTES);
    }

    /**
     * 注册
     *
     * @param registerVO 前端请求信息：邮箱、验证码、用户名、密码
     */
    @Override
    public void register(RegisterVO registerVO) {
        String key = RedisConstant.RECEIVE_MAIL + registerVO.getEmail();
        // 验证验证码
        this.codeVerify(key, registerVO.getCode());
        // 用户名已存在
        Account byUsername = accountMapper.getByUsername(registerVO.getUsername());
        if (!Objects.isNull(byUsername)) {
            throw new RegisterException(HttpStatus.BAD_REQUEST.value(), MessageConstant.USERNAME_ALREADY_EXISTS);
        }
        // 邮箱已存在
        Account byEmail = accountMapper.getByEmail(registerVO.getEmail());
        if (!Objects.isNull(byEmail)) {
            throw new RegisterException(HttpStatus.BAD_REQUEST.value(), MessageConstant.EMAIL_ALREADY_EXISTS);
        }
        Account account = new Account();
        account.setUsername(registerVO.getUsername());
        account.setPassword(encoder.encode(registerVO.getPassword()));
        account.setEmail(registerVO.getEmail());
        account.setNikeName(registerVO.getUsername());
        Date date = new Date(new java.util.Date().getTime());
        account.setGmtCreate(date);
        account.setGmtModified(date);
        account.setAuthority(DefaultConstant.DEFAULT_AUTHORITY);
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
     * @param resetPwVO 前端请求信息：邮箱、验证码、新密码
     */
    @Override
    public void resetPw(ResetPwVO resetPwVO) {
        // 验证验证码
        String key = RedisConstant.RECEIVE_MAIL + resetPwVO.getEmail();
        this.codeVerify(key, resetPwVO.getCode());
        // 还未注册
        Account byEmail = accountMapper.getByEmail(resetPwVO.getEmail());
        if (Objects.isNull(byEmail)) {
            throw new ResetPasswordException(HttpStatus.BAD_REQUEST.value(), MessageConstant.ACCOUNT_NOT_FOUND);
        }
        // 新旧密码一样
        if (encoder.matches(resetPwVO.getPassword(), byEmail.getPassword())) {
            throw new ResetPasswordException(HttpStatus.BAD_REQUEST.value(), MessageConstant.NEW_PASSWORD_SAME_AS_OLD_PASSWORD);
        }
        // 更新密码
        resetPwVO.setGmtModified(new Date(new java.util.Date().getTime()));
        resetPwVO.setPassword(encoder.encode(resetPwVO.getPassword()));
        int len = accountMapper.updatePwByEmail(resetPwVO);
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
     * 创建邮件
     *
     * @param subject 邮件主题
     * @param text    邮件内容
     * @param toEmail 收件人
     * @return 邮件
     */
    private SimpleMailMessage createMailMessage(String subject, String text, String toEmail) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject(subject);
        mailMessage.setText(text);
        mailMessage.setTo(toEmail);
        mailMessage.setFrom(fromEmail);
        return mailMessage;
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

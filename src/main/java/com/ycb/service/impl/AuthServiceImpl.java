package com.ycb.service.impl;

import com.ycb.entity.Account;
import com.ycb.entity.AccountDTO;
import com.ycb.entity.Const;
import com.ycb.mapper.AccountMapper;
import com.ycb.service.AuthService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;

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
    public String sendEmail(String email, String type, String ip) {
        if (frequent(ip)) {
            return "请求频繁";
        }
        // 生成验证码
        Random random = new Random();
        int code = random.nextInt(89999) + 10000;
        SimpleMailMessage mailMessage = switch (type) {
            case "register" ->
                    this.createMailMessage(code + "是你的注册验证码", "你好！在创建帐号之前，你需要一个简单的步骤，让我们确保这是正确的邮件地址————\n\n请输入此验证码以开始使用：" + code + "\n验证码有效时间3分钟。如非本人操作可忽略。", email);
            case "resetPw" ->
                    this.createMailMessage(code + "是你的重置密码验证码", "亲爱的用户：\n注意！你正在进行重置密码操作！\n请输入此验证码以重置密码：" + code + "，验证码有效时间3分钟。\n\n提供给他人会导致账户被盗和资产损失，如非本人操作，请尽快修改密码。\n如果是您本人操作，请忽略本次提醒。", email);
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
        try {
            javaMailSender.send(mailMessage);
        } catch (MailException e) {
            return e.getMessage();
        }
        // 邮箱、验证码存入redis，有效时间3分钟
        stringRedisTemplate.opsForValue()
                .set(Const.RECEIVE_MAIL + email, String.valueOf(code), 3, TimeUnit.MINUTES);
        return null;
    }

    /**
     * 注册
     *
     * @param accountDTO 前端请求信息：邮箱、验证码、用户名、密码
     * @return 请求结果
     */
    @Override
    public String register(AccountDTO accountDTO) {
        String key = Const.RECEIVE_MAIL + accountDTO.getEmail();
        // 验证验证码
        String message = this.codeVerify(key, accountDTO.getCode());
        if (message != null) {
            return message;
        }
        Account byUsername = accountMapper.getByUsername(accountDTO.getUsername());
        if (!Objects.isNull(byUsername)) {
            return "用户名已存在";
        }
        Account byEmail = accountMapper.getByEmail(accountDTO.getEmail());
        if (!Objects.isNull(byEmail)) {
            return "该邮箱已注册";
        }
        Account account = new Account();
        account.setUsername(accountDTO.getUsername());
        account.setPassword(encoder.encode(accountDTO.getPassword()));
        account.setEmail(accountDTO.getEmail());
        account.setCreateTime(new Date());
        account.setUpdateTime(new Date());
        account.setAuthority("user");
        int len = accountMapper.save(account);
        if (len < 1) {
            return "系统异常，请稍后重试";
        }
        // 删除redis中的验证码数据
        if (Boolean.TRUE.equals(stringRedisTemplate.hasKey(key))) {
            stringRedisTemplate.delete(key);
        }
        return null;
    }

    /**
     * 重置密码
     *
     * @param accountDTO 前端请求信息：邮箱、验证码、新密码
     * @return 请求结果
     */
    @Override
    public String resetPw(AccountDTO accountDTO) {
        // 验证验证码
        String key = Const.RECEIVE_MAIL + accountDTO.getEmail();
        String message = this.codeVerify(key, accountDTO.getCode());
        if (message != null) {
            return message;
        }
        // 还未注册
        Account byEmail = accountMapper.getByEmail(accountDTO.getEmail());
        if (Objects.isNull(byEmail)) {
            return "请先注册";
        }
        // 新旧密码一样
        if (encoder.matches(accountDTO.getPassword(), byEmail.getPassword())) {
            return "新密码不能与旧密码一样";
        }
        // 更新密码
        accountDTO.setPassword(encoder.encode(accountDTO.getPassword()));
        int len = accountMapper.updatePwByEmail(accountDTO);
        if (len < 1) {
            return "系统异常，请稍后重试";
        }
        // 删除redis中的验证码数据
        if (Boolean.TRUE.equals(stringRedisTemplate.hasKey(key))) {
            stringRedisTemplate.delete(key);
        }
        return null;
    }

    /**
     * 验证验证码
     */
    private String codeVerify(String codeKey, String code) {
        if (Boolean.FALSE.equals(stringRedisTemplate.hasKey(codeKey))) {
            return "请先获取验证码";
        }
        String rightCode = stringRedisTemplate.opsForValue().get(codeKey);
        if (rightCode == null) {
            return "请先获取验证码";
        }
        if (!rightCode.equals(code)) {
            return "验证码错误";
        }
        return null;
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
     * 是否频繁请求
     */
    private boolean frequent(String ip) {
        String key = Const.SEND_MAIL_FREQUENT + ip;
        if (Boolean.TRUE.equals(stringRedisTemplate.hasKey(key))) {
            return true;
        } else {
            stringRedisTemplate.opsForValue().set(key, "", 1, TimeUnit.MINUTES);
        }
        return false;
    }
}

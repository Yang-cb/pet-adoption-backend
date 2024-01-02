package com.ycb.common.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
public class EmailUtils {
    // 发件人
    @Value("${spring.mail.username}")
    private String fromEmail;

    /**
     * 创建邮件
     *
     * @param subject 邮件主题
     * @param text    邮件内容
     * @param toEmail 收件人
     * @return 邮件
     */
    public SimpleMailMessage createMailMessage(String subject, String text, String toEmail) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject(subject);
        mailMessage.setText(text);
        mailMessage.setTo(toEmail);
        mailMessage.setFrom(fromEmail);
        return mailMessage;
    }
}

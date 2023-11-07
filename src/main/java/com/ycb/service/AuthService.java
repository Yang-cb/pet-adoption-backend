package com.ycb.service;

import com.ycb.entity.vo.request.RegisterVO;
import com.ycb.entity.vo.request.ResetPwVO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService extends UserDetailsService {
    /**
     * 发送邮件
     *
     * @param email 邮箱
     * @param ip    ip地址
     * @param type  邮件类型
     * @return 邮件发送结果
     */
    String sendEmail(String email, String type, String ip);

    /**
     * 注册
     * @param registerVO 前端请求信息：邮箱、验证码、用户名、密码
     * @return 请求结果
     */
    String register(RegisterVO registerVO);

    String resetPw(ResetPwVO resetPwVO);
}

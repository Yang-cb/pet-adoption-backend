package com.ycb.service;

import com.ycb.pojo.dto.RegisterDTO;
import com.ycb.pojo.dto.ResetPwDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * 认证的服务接口
 */
public interface AuthService extends UserDetailsService {
    /**
     * 发送邮件
     *
     * @param email 邮箱
     * @param ip    ip地址
     * @param type  邮件类型
     */
    void sendEmail(String email, String type, String ip);

    /**
     * 注册
     * @param registerDTO 前端请求信息：邮箱、验证码、用户名、密码
     */
    void register(RegisterDTO registerDTO);

    /**
     * 重置密码
     * @param resetPwDTO 前端请求信息：邮箱、验证码、新密码
     */
    void resetPw(ResetPwDTO resetPwDTO);
}

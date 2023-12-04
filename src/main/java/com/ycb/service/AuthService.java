package com.ycb.service;

import com.ycb.entity.vo.request.RegisterVO;
import com.ycb.entity.vo.request.ResetPwVO;
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
     * @param registerVO 前端请求信息：邮箱、验证码、用户名、密码
     */
    void register(RegisterVO registerVO);

    /**
     * 重置密码
     * @param resetPwVO 前端请求信息：邮箱、验证码、新密码
     */
    void resetPw(ResetPwVO resetPwVO);
}

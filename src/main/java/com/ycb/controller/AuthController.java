package com.ycb.controller;

import com.ycb.entity.AccountDTO;
import com.ycb.entity.RestBean;
import com.ycb.service.AuthService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.Email;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Resource
    private AuthService authService;

    /**
     * 发送邮件
     *
     * @param email   接收者
     * @param type    邮件类型：register注册，resetPw重置密码
     * @param request 请求，用于获取请求ip
     * @return 请求结果
     */
    @GetMapping("/sendEmail")
    public RestBean<String> sendEmail(@RequestParam @Email String email, @RequestParam String type, HttpServletRequest request) {
        String ip = request.getRemoteAddr();
        String message = authService.sendEmail(email, type, ip);
        return message == null ? RestBean.success("发送成功") : RestBean.failure(403, message);
    }


    /**
     * 注册
     *
     * @param accountDTO 前端请求信息：邮箱、验证码、用户名、密码
     * @return 请求结果
     */
    @PostMapping("/register")
    public RestBean<String> register(@RequestBody AccountDTO accountDTO) {
        String message = authService.register(accountDTO);
        return message == null ? RestBean.success("注册成功") : RestBean.failure(403, message);
    }

    /**
     * 重置密码
     *
     * @param accountDTO 前端请求信息：邮箱、验证码、新密码
     * @return 请求结果
     */
    @PostMapping("/resetPw")
    public RestBean<String> resetPw(@RequestBody AccountDTO accountDTO) {
        String message = authService.resetPw(accountDTO);
        return message == null ? RestBean.success("重置密码成功") : RestBean.failure(403, message);
    }
}

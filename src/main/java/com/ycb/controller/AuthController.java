package com.ycb.controller;

import com.ycb.entity.vo.request.RegisterVO;
import com.ycb.entity.RestBean;
import com.ycb.entity.vo.request.ResetPwVO;
import com.ycb.service.AuthService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.*;

/**
 * 认证控制器
 */
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
    public RestBean<String> sendEmail(@RequestParam @Email @NotNull String email,
                                      @RequestParam @NotNull String type,
                                      HttpServletRequest request) {
        String ip = request.getRemoteAddr();
        authService.sendEmail(email, type, ip);
        return RestBean.success();
    }


    /**
     * 注册
     *
     * @param registerVO 前端请求信息：邮箱、验证码、用户名、密码
     * @return 请求结果
     */
    @PostMapping("/register")
    public RestBean<String> register(@Valid @RequestBody RegisterVO registerVO) {
        authService.register(registerVO);
        return RestBean.success();
    }

    /**
     * 重置密码
     *
     * @param resetPwVO 前端请求信息：邮箱、验证码、新密码
     * @return 请求结果
     */
    @PostMapping("/resetPw")
    public RestBean<String> resetPw(@Valid @RequestBody ResetPwVO resetPwVO) {
        authService.resetPw(resetPwVO);
        return RestBean.success();
    }
}

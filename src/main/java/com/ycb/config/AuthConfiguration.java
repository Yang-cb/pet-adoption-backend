package com.ycb.config;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.ycb.entity.dto.Account;
import com.ycb.entity.RestBean;
import com.ycb.entity.vo.response.LoginVO;
import com.ycb.mapper.AccountMapper;
import com.ycb.utils.JwtUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;

@Component
public class AuthConfiguration {

    @Resource
    private JwtUtils jwtUtils;
    @Resource
    private AccountMapper accountMapper;

    /**
     * 登陆成功
     */
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        // 获取认证成功的用户信息
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        // 获取数据库中的用户信息
        Account account = accountMapper.getByUsername(userDetails.getUsername());
        // 生成jwt
        String jwt = jwtUtils.createJwt(account.getId(), userDetails);
        LoginVO vo = new LoginVO();
        vo.setId(account.getId());
        vo.setUsername(account.getUsername());
        vo.setAuthority(account.getAuthority());
        vo.setToken(jwt);
        vo.setExpireTime(jwtUtils.getExpireTime());
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(RestBean.success(vo).jsonToString());
    }

    /**
     * 登陆失败
     */
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(RestBean.failure(401, exception.getMessage()).jsonToString());
    }

    /**
     * 退出登录
     */
    public void onLogoutSuccess(HttpServletRequest request,
                                HttpServletResponse response,
                                Authentication authentication) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        // jwt写入黑名单
        DecodedJWT jwt = jwtUtils.parseJwt(request.getHeader("Authorization"));
        String message = jwtUtils.jwtJoinBlackList(jwt);
        if (message == null) {
            writer.write(RestBean.success("退出成功").jsonToString());
        } else {
            writer.write(RestBean.failure(403, message).jsonToString());
        }
    }

    /**
     * 异常：未登录时操作
     */
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(RestBean.failure(401, authException.getMessage()).jsonToString());
    }

    /**
     * 异常：无权限操作
     */
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(RestBean.failure(403, accessDeniedException.getMessage()).jsonToString());
    }
}

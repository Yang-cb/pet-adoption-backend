package com.ycb.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 跨域过滤器
 */
@Component
@Order(-102)
public class CorsFilter extends HttpFilter {
    @Value("${spring.security.frontend.url}")
    private String frontendUrl;
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        this.addCorsHeader(request, response);
        chain.doFilter(request, response);
    }

    /**
     * 添加所有跨域相关响应头
     *
     * @param request  请求
     * @param response 响应
     */
    private void addCorsHeader(HttpServletRequest request, HttpServletResponse response) {
        // request.getHeader("Origin")
        response.addHeader("Access-Control-Allow-Origin", frontendUrl);
        response.addHeader("Access-Control-Allow-Methods", "GET,POST,PUT");
        response.addHeader("Access-Control-Allow-Headers", "Authorization, Content-Type");
    }
}

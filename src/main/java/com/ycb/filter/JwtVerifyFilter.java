package com.ycb.filter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.ycb.utils.JwtUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

/**
 * jwt验证过滤器
 */
@Component
public class JwtVerifyFilter extends OncePerRequestFilter {
    @Resource
    private JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        DecodedJWT jwt = jwtUtils.parseJwt(request.getHeader("Authorization"));
        // jwt为空，放行
        if (jwt == null) {
            filterChain.doFilter(request, response);
            return;
        }
        UserDetails userDetails = jwtUtils.jwtToUser(jwt);
        // 如果用户信息为空，直接放行
        if (Objects.isNull(userDetails)) {
            filterChain.doFilter(request, response);
            return;
        }
        // 如果用户信息不为空，将用户信息存入上下文
        UsernamePasswordAuthenticationToken authentication
                = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }
}

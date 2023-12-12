package com.ycb.config;

import com.ycb.common.constant.AuthorityConstant;
import com.ycb.filter.JwtVerifyFilter;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 * 安全配置
 */
@Configuration
public class SecurityConfiguration {
    @Resource
    private AuthConfiguration authConfiguration;
    @Resource
    private JwtVerifyFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                // 基于token，不需要session
                .sessionManagement(conf -> conf.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // 不需要csrf
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(conf ->
                        // 当前路径允许任何人请求
                        conf.requestMatchers("/api/auth/**").permitAll()
                                // 管理员路径，需要管理员权限
                                .requestMatchers("/api/admin/**").hasAnyAuthority('[' + AuthorityConstant.ADMIN_AUTHORITY + ']')
                                // 除上述路径，任何人必须认证后请求
                                .anyRequest().authenticated()
                )
                .formLogin(conf ->
                        // 指定登录url
                        conf.loginProcessingUrl("/api/auth/login")
                                // 登录成功处理
                                .successHandler(authConfiguration::onAuthenticationSuccess)
                                // 登录失败处理
                                .failureHandler(authConfiguration::onAuthenticationFailure)
                )
                .logout(conf ->
                        // 指定退出登录url
                        conf.logoutUrl("/api/auth/logout")
                                // 退出登录处理
                                .logoutSuccessHandler(authConfiguration::onLogoutSuccess))
                // 添加jwt过滤器
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(conf ->
                        // 未登录时操作
                        conf.authenticationEntryPoint(authConfiguration::commence)
                                // 无权限操作
                                .accessDeniedHandler(authConfiguration::handle))
                .build();
    }


}

package com.ycb.entity.vo.request;

import lombok.Data;

/**
 * 注册请求封装对象
 */
@Data
public class RegisterVO {
    /**
     * 邮箱
     */
    private String email;
    /**
     * 验证码
     */
    private String code;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
}

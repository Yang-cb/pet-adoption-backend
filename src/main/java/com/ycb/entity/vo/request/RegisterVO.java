package com.ycb.entity.vo.request;

import lombok.Data;

/**
 * 注册请求封装对象
 */
@Data
public class RegisterVO {
    private String email;
    private String code;
    private String username;
    private String password;
}

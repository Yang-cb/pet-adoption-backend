package com.ycb.pojo.vo;

import lombok.Data;

import java.util.Date;

/**
 * 登录成功后，返回给前端的数据
 */
@Data
public class LoginVO {
    /**
     * 用户id
     */
    private Integer id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户权限
     */
    private String authority;
    /**
     * token
     */
    private String token;
    /**
     * token过期时间
     */
    private Date expireTime;
}

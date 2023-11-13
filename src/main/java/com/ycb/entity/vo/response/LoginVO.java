package com.ycb.entity.vo.response;

import lombok.Data;

import java.util.Date;

/**
 * 登录成功后，返回给前端的数据
 */
@Data
public class LoginVO {
    private Integer id;
    private String username;
    private String authority;
    private String token;
    private Date expireTime;
}

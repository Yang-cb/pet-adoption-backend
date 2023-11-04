package com.ycb.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Account {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private Date createTime;
    private Date updateTime;
    // 权限
    private String authority;
}

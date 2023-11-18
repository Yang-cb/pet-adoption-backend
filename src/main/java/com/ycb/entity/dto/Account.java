package com.ycb.entity.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class Account {
    private Integer accountId;
    /**
     * 头像ID
     */
    private String pictureId;
    private String username;
    private String password;
    private String email;
    private String authority; // 权限
    private String location; // 地区
    private String nikeName; // 昵称
    private Data birthday; // 出生日期
    private Integer sex; // 性别
    private String signature; // 签名
    private Date gmtCreate;
    private Date gmtModified;
    private Integer isDelete;
}

package com.ycb.pojo.entity;

import lombok.Data;

import java.sql.Date;

@Data
public class Account {
    /**
     * 用户id
     */
    private Integer accountId;
    /**
     * 头像ID
     */
    private String pictureId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 权限
     */
    private String authority;
    /**
     * 地区
     */
    private String location;
    /**
     * 昵称
     */
    private String nikeName;
    /**
     * 出生日期
     */
    private Data birthday;
    /**
     * 性别
     */
    private Integer sex;
    /**
     * 签名
     */
    private String signature;
    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 修改时间
     */
    private Date gmtModified;
    /**
     * 是否删除
     */
    private Integer isDelete;
}

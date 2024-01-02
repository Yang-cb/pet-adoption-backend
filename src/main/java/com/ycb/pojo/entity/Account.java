package com.ycb.pojo.entity;

import lombok.Data;

import java.sql.Date;
import java.time.LocalDateTime;

@Data
public class Account {
    /**
     * 用户id
     */
    private Integer accountId;
    /**
     * 账号状态 1启用0禁用
     */
    private Integer accountStatus;
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
    private Date birthday;
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
    private LocalDateTime gmtCreate;
    /**
     * 修改时间
     */
    private LocalDateTime gmtModified;
    /**
     * 是否删除
     */
    private Integer isDelete;
}

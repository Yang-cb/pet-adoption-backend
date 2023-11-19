package com.ycb.entity.vo.response;

import lombok.Data;

import java.sql.Date;

/**
 * 用户信息（含头像）
 */
@Data
public class AccountVO {
    /**
     * 用户id
     */
    private Integer accountId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 地址
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
     * 头像
     */
    private String picName;
}

package com.ycb.entity.vo.response;

import lombok.Data;

import java.sql.Date;

@Data
public class AccountVO {
    private String username; // 用户名
    private String email; // 邮箱
    private String location; // 地址
    private String nikeName; // 昵称
    private Date birthday; // 出生日期
    private Integer sex; // 性别
    private String signature; // 签名
    private String picName; // 头像
}

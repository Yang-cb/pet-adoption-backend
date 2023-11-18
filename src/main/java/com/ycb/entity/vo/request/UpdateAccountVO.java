package com.ycb.entity.vo.request;

import lombok.Data;

import java.sql.Date;

/**
 * 修改用户信息
 */
@Data
public class UpdateAccountVO {
    private Integer id; // 用户id
    private String location; // 地址
    private String nikeName; // 昵称
    private Date birthday; // 出生日期
    private Integer sex; // 性别
    private String signature; // 签名
}

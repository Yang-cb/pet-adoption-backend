package com.ycb.pojo.vo;

import lombok.Data;

/**
 * 关注列表
 */
@Data
public class FollowVO {
    /**
     * 用户id
     */
    private Integer accountId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户昵称
     */
    private String nikeName;
    /**
     * 头像
     */
    private String picName;
    /**
     * 用户简介
     */
    private String signature;
}

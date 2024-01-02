package com.ycb.pojo.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 关注实体类
 */
@Data
public class Follow {
    /**
     * 自增id
     */
    private Integer followId;
    /**
     * 关注者id
     */
    private Integer followerId;
    /**
     * 被关注者id
     */
    private Integer followedId;
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

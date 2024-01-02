package com.ycb.pojo.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 评论表
 */
@Data
public class Review {
    /**
     * 自增id
     */
    private Integer reviewId;
    /**
     * 评论者id
     */
    private Integer reviewerId;
    /**
     * 被评论宠物id
     */
    private Integer reviewedPetId;
    /**
     * 评论内容
     */
    private String reviewText;
    /**
     * 评论时间
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

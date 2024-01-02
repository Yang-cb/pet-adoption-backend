package com.ycb.pojo.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 评论VO
 */
@Data
public class AllReviewVO {
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
    /**
     * 评论者昵称
     */
    private String nikeName;
    /**
     * 评论者头像
     */
    private String picName;
    /**
     * 评论者用户名
     */
    private String username;
}

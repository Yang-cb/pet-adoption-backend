package com.ycb.pojo.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 添加评论
 */
@Data
public class AddReviewDTO {
    /**
     * 评论者id
     */
    @NotNull(message = "id不能为空")
    private Integer reviewerId;
    /**
     * 被评论宠物id
     */
    @NotNull(message = "id不能为空")
    private Integer reviewedPetId;
    /**
     * 评论内容
     */
    @NotNull(message = "评论内容不能为空")
    @Pattern(regexp = "^(?!\\s*$).{1,100}$", message = "评论内容不合法")
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

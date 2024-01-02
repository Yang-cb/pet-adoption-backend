package com.ycb.pojo.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DeleteReviewDTO {
    /**
     * 账号id
     */
    @NotNull(message = "id不能为空")
    private Integer accountId;
    /**
     * 评论id
     */
    @NotNull(message = "id不能为空")
    private Integer reviewId;
    private Integer isDelete;
    private LocalDateTime gmtModified;
}

package com.ycb.pojo.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 关注者id和被关注者id数据传输对象
 */
@Data
public class FerIdFedIdDTO {
    /**
     * 关注者id
     */
    @NotNull(message = "id不能为空")
    private Integer followerId;
    /**
     * 被关注者id
     */
    @NotNull(message = "id不能为空")
    private Integer followedId;
}

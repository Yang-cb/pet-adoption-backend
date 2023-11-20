package com.ycb.entity.vo.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 收藏账号宠物请求封装
 */
@Data
public class AccIdPetIdVO {
    /**
     * 用户id
     */
    @NotNull(message = "用户id不能为空")
    private Integer accId;
    /**
     * 宠物id
     */
    @NotNull(message = "宠物id不能为空")
    private Integer petId;
}

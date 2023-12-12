package com.ycb.pojo.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 修改布告状态（审核）
 */
@Data
public class UpdateBulletinStatusDTO {
    /**
     * 宠物id
     */
    @NotNull(message = "宠物id不能为空")
    private Integer petId;
    /**
     * 要修改的布告状态
     */
    @NotNull(message = "status不能为空")
    @Min(value = 1, message = "status格式有误")
    @Max(value = 2, message = "status格式有误")
    private Integer bulletinStatus;
}

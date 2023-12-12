package com.ycb.pojo.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 管理员更新想领状态（是否审核通过）
 */
@Data
public class UpdateWantStatus12DTO {
    /**
     * 发布宠物布告的账号id
     */
    @NotNull(message = "accountId不能为空")
    private Integer accountId;
    /**
     * 想领id
     */
    @NotNull(message = "wantId不能为空")
    private Integer wantId;
    /**
     * 初始状态
     */
    @NotNull(message = "status不能为空")
    @Min(value = 0, message = "status格式有误")
    @Max(value = 0, message = "status格式有误")
    private Integer initStatus;
    /**
     * 想领状态
     */
    @NotNull(message = "status不能为空")
    @Min(value = 1, message = "status格式有误")
    @Max(value = 2, message = "status格式有误")
    private Integer wantStatus;
}

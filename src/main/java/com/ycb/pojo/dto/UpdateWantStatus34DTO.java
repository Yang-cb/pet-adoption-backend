package com.ycb.pojo.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 用户更新想领状态（是否同意请求）
 */
@Data
public class UpdateWantStatus34DTO {
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
    @Min(value = 1, message = "status格式有误")
    @Max(value = 1, message = "status格式有误")
    private Integer initStatus;
    /**
     * 想领状态
     */
    @NotNull(message = "status不能为空")
    @Min(value = 3, message = "status格式有误")
    @Max(value = 4, message = "status格式有误")
    private Integer wantStatus;
}

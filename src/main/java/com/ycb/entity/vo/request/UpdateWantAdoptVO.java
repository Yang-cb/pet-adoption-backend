package com.ycb.entity.vo.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 更新想领状态
 */
@Data
public class UpdateWantAdoptVO {
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
     * 想领状态
     */
    @NotNull(message = "status不能为空")
    private Integer wantStatus;
}

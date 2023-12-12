package com.ycb.pojo.dto;

import lombok.Data;

/**
 * 更新想领状态（中间态）
 */
@Data
public class UpdateWantStatusDTO {
    /**
     * 发布宠物布告的账号id
     */
    private Integer accountId;
    /**
     * 想领id
     */
    private Integer wantId;
    /**
     * 初始状态
     */
    private Integer initStatus;
    /**
     * 想领状态
     */
    private Integer wantStatus;
}

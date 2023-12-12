package com.ycb.pojo.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateAccountStatusDTO {
    @NotNull(message = "宠物id不能为空")
    private Integer AccountId;

    @NotNull(message = "status不能为空")
    @Min(value = 0, message = "status格式有误")
    @Max(value = 1, message = "status格式有误")
    private Integer AccountStatus;
}

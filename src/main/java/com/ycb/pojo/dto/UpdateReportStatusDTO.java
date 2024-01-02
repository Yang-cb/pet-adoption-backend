package com.ycb.pojo.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 管理员修改用户或宠物状态
 */
@Data
public class UpdateReportStatusDTO {
    /**
     * 自增id
     */
    private Integer reportId;
    /**
     * 要修改成的状态
     */
    @NotNull(message = "status不能为空")
    @Min(value = 0, message = "status格式有误")
    @Max(value = 2, message = "status格式有误")
    private Integer toStatus;
    /**
     * 禁用的id
     */
    @NotNull(message = "id不能为空")
    private Integer disableId;
    /**
     * 禁用的类型
     */
    @NotNull(message = "类型不能为空")
    private Integer disableType;
    /**
     * 操作者id
     */
    @NotNull(message = "id不能为空")
    private Integer modifiedId;
    /**
     * 禁用原因
     */
    private String disableCheck;
    /**
     * 禁用原因
     */
    private String disableText;
    /**
     * 创建时间
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

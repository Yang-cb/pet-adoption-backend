package com.ycb.pojo.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 管理员禁用表
 */
@Data
public class AdminOperationStatus {
    /**
     * 自增id
     */
    private Integer adminOperationId;
    /**
     * 禁用的id
     */
    private Integer disableId;
    /**
     * 禁用的类型
     */
    private Integer disableType;
    /**
     * 操作者id
     */
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

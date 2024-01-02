package com.ycb.pojo.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户举报表
 */
@Data
public class Report {
    /**
     * 自增id
     */
    private Integer reportId;
    /**
     * 被举报的id
     */
    private Integer reportedId;
    /**
     * 举报类型
     */
    private Integer reportType;
    /**
     * 举报者id
     */
    private Integer whistleblowerId;
    /**
     * 举报原因
     */
    private String reportCheck;
    /**
     * 举报原因
     */
    private String reportText;
    /**
     * 状态
     */
    private Integer reportStatus;
    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;
    /**
     * 修改时间
     */
    private LocalDateTime gmtModified;
    /**
     * 是否删除 1是0否
     */
    private Integer isDelete;
}

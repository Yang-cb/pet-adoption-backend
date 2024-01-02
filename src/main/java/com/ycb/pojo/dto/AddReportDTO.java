package com.ycb.pojo.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AddReportDTO {
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
     * 举报状态
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
     * 是否删除
     */
    private Integer isDelete;
}

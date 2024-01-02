package com.ycb.pojo.dto;

import lombok.Data;

@Data
public class PageReportDTO {
    /**
     * 页码
     */
    private Integer page;
    /**
     * 每页显示条数
     */
    private Integer pageSize;
    /**
     * 举报状态
     */
    private Integer reportStatus;
    /**
     * 举报类型
     */
    private Integer reportType;
}

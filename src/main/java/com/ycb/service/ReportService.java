package com.ycb.service;

import com.ycb.pojo.dto.AddReportDTO;

public interface ReportService {
    /**
     * 添加举报
     *
     * @param dto 举报信息
     */
    void addReport(AddReportDTO dto);
}

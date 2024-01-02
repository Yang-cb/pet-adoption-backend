package com.ycb.service.impl;

import com.ycb.common.constant.StatusConstant;
import com.ycb.mapper.ReportMapper;
import com.ycb.pojo.dto.AddReportDTO;
import com.ycb.service.ReportService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl implements ReportService {
    @Resource
    private ReportMapper reportMapper;

    @Override
    public void addReport(AddReportDTO dto) {
        dto.setReportStatus(StatusConstant.PENDING_REVIEW);
        reportMapper.addReport(dto);
    }
}

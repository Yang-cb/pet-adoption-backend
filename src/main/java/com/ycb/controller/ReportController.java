package com.ycb.controller;

import com.ycb.common.result.RestBean;
import com.ycb.pojo.dto.AddReportDTO;
import com.ycb.service.ReportService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 举报控制器
 */
@RestController
public class ReportController {
    @Resource
    private ReportService reportService;

    /**
     * 添加举报
     *
     * @param dto 举报信息
     * @return 成功与否
     */
    @PostMapping("/api/report/addReport")
    public RestBean<Void> addReport(@RequestBody @Valid AddReportDTO dto) {
        reportService.addReport(dto);
        return RestBean.success();
    }
}

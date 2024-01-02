package com.ycb.mapper;

import com.ycb.annotation.AutoFill;
import com.ycb.common.enumerate.OperationType;
import com.ycb.pojo.dto.AddReportDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReportMapper {
    @Insert("insert into `pet-adoption`.report" +
            "(reported_id, report_type, whistleblower_id, report_check,report_text, report_status,gmt_create, gmt_modified)" +
            " value(#{reportedId}, #{reportType}, #{whistleblowerId}, #{reportCheck}, #{reportText}, #{reportStatus}, #{gmtCreate}, #{gmtModified})")
    @AutoFill(OperationType.INSERT)
    void addReport(AddReportDTO dto);
}

package com.ycb.mapper;

import com.github.pagehelper.Page;
import com.ycb.annotation.AutoFill;
import com.ycb.common.enumerate.OperationType;
import com.ycb.pojo.dto.*;
import com.ycb.pojo.vo.AllReportVO;
import com.ycb.pojo.vo.AllWantAdoptVO;
import com.ycb.pojo.vo.AllAccountVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapper {
    /**
     * 获取所有用户信息
     *
     * @param dto 分页信息
     * @return 所有用户信息
     */
    Page<AllAccountVO> getAllAccount(PageAccountDTO dto);

    /**
     * 获取所有想要领养的信息
     *
     * @param dto 分页信息
     * @return 所有想要领养的信息
     */
    Page<AllWantAdoptVO> getAllWantAdopt(PageWantAdoptDTO dto);

    /**
     * 获取所有举报信息
     *
     * @param dto 分页信息
     * @return 所有举报信息
     */
    Page<AllReportVO> getAllReport(PageReportDTO dto);

    /**
     * 修改布告状态（审核）
     *
     * @param dto 布告id 要修改的布告状态
     */
    @AutoFill(OperationType.UPDATE)
    void updateBulletinStatus(UpdateBulletinStatusDTO dto);

    /**
     * 修改用户状态（禁用、启用）
     *
     * @param dto 用户id和要修改的用户状态
     */
    @AutoFill(OperationType.UPDATE)
    void updateAccountStatus(UpdateReportStatusDTO dto);

    /**
     * 添加已禁用信息
     *
     * @param dto 用户id和要修改的用户状态
     */
    @AutoFill(OperationType.INSERT)
    void addDisable(UpdateReportStatusDTO dto);

    /**
     * 修改举报状态（通过，未通过）
     *
     * @param toStatus 要修改的举报状态
     */
    @AutoFill(OperationType.UPDATE)
    void updateReportStatus(UpdateReportStatusDTO toStatus);
}

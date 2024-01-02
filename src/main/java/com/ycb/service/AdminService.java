package com.ycb.service;

import com.ycb.common.result.PageResult;
import com.ycb.pojo.dto.*;

public interface AdminService {
    /**
     * 获取所有用户信息
     *
     * @param dto 分页信息
     * @return 所有用户信息
     */
    PageResult getAllAccount(PageAccountDTO dto);

    /**
     * 获取所有想要领养的信息
     *
     * @param dto 分页信息
     * @return 所有想要领养的信息
     */
    PageResult getAllWantAdopt(PageWantAdoptDTO dto);

    /**
     * 获取所有举报信息
     *
     * @param dto 分页信息
     * @return 所有举报信息
     */
    PageResult getAllReport(PageReportDTO dto);

    /**
     * 修改布告状态（审核）
     *
     * @param dto 宠物id和要修改的布告状态
     */
    void updateBulletinStatus(UpdateBulletinStatusDTO dto);

    /**
     * 修改用户状态（禁用、启用）
     *
     * @param dto 用户id和要修改的用户状态
     */
    void updateReportStatus(UpdateReportStatusDTO dto);
}

package com.ycb.controller;

import com.ycb.common.result.PageResult;
import com.ycb.common.result.RestBean;
import com.ycb.pojo.dto.*;
import com.ycb.service.AdminService;
import com.ycb.service.PetService;
import com.ycb.service.WantAdoptService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

/**
 * 管理员控制器
 */
@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Resource
    private AdminService adminService;

    @Resource
    private WantAdoptService wantAdoptService;

    @Resource
    private PetService petService;

    /**
     * 获取所有账户信息
     *
     * @param dto 分页信息
     * @return 所有账户信息
     */
    @GetMapping("/getAllAccount")
    public RestBean<PageResult> getAllAccount(PageAccountDTO dto) {
        PageResult pageResult = adminService.getAllAccount(dto);
        return RestBean.success(pageResult);
    }

    /**
     * 获取所有宠物信息
     *
     * @param dto 分页信息
     * @return 所有宠物信息
     */
    @GetMapping("/getAllPet")
    public RestBean<PageResult> getAllPet(PagePetDTO dto) {
        PageResult pageResult = petService.page(dto);
        return RestBean.success(pageResult);
    }

    /**
     * 获取所有想要领养的信息
     *
     * @param dto 分页信息
     * @return 所有想要领养的信息
     */
    @GetMapping("/getAllWantAdopt")
    public RestBean<PageResult> getAllWantAdopt(PageWantAdoptDTO dto) {
        PageResult pageResult = adminService.getAllWantAdopt(dto);
        return RestBean.success(pageResult);
    }

    /**
     * 获取所有举报信息
     *
     * @param dto 分页信息
     * @return 所有举报信息
     */
    @GetMapping("/getAllReport")
    public RestBean<PageResult> getAllReport(PageReportDTO dto) {
        PageResult pageResult = adminService.getAllReport(dto);
        return RestBean.success(pageResult);
    }

    /**
     * 修改布告状态（审核）
     *
     * @param dto 宠物id和要修改的布告状态
     */
    @PutMapping("/updateBulletinStatus")
    public RestBean<String> updateBulletinStatus(@Valid @RequestBody UpdateBulletinStatusDTO dto) {
        adminService.updateBulletinStatus(dto);
        return RestBean.success();
    }

    /**
     * 修改举报状态（禁用、启用）
     *
     * @param dto 举报类型、举报id、要修改的举报状态等
     */
    @PutMapping("/updateReportStatus")
    public RestBean<String> updateReportStatus(@Valid @RequestBody UpdateReportStatusDTO dto) {
        adminService.updateReportStatus(dto);
        return RestBean.success();
    }

    /**
     * 管理员更新想领状态（是否审核通过）
     *
     * @param dto 想领id和要修改的想领状态
     */
    @PutMapping("/updateWantStatus")
    public RestBean<String> updateWantStatus(@Valid @RequestBody UpdateWantStatus12DTO dto) {
        UpdateWantStatusDTO updateWantStatusDTO = new UpdateWantStatusDTO();
        BeanUtils.copyProperties(dto, updateWantStatusDTO);
        wantAdoptService.updateWantAdoptStatus(updateWantStatusDTO);
        return RestBean.success();
    }
}

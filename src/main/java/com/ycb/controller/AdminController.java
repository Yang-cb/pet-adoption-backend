package com.ycb.controller;

import com.ycb.common.result.RestBean;
import com.ycb.pojo.dto.*;
import com.ycb.pojo.vo.admin.AdminAllAccountVO;
import com.ycb.pojo.vo.admin.AdminAllPetBulVO;
import com.ycb.pojo.vo.admin.AdminAllWantAdoptVO;
import com.ycb.service.AdminService;
import com.ycb.service.WantAdoptService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    /**
     * 获取所有账户信息
     *
     * @return 所有账户信息
     */
    @GetMapping("/getAllAccount")
    public RestBean<List<AdminAllAccountVO>> getAllAccount() {
        return RestBean.success(adminService.getAllAccount());
    }

    /**
     * 获取所有宠物信息
     *
     * @return 所有宠物信息
     */
    @GetMapping("/getAllPet")
    public RestBean<List<AdminAllPetBulVO>> getAllPet() {
        return RestBean.success(adminService.getAllPet());
    }

    /**
     * 获取所有想要领养的信息
     *
     * @return 所有想要领养的信息
     */
    @GetMapping("/getAllWantAdopt")
    public RestBean<List<AdminAllWantAdoptVO>> getAllWantAdopt() {
        return RestBean.success(adminService.getAllWantAdopt());
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
     * 修改用户状态（禁用、启用）
     *
     * @param dto 用户id和要修改的用户状态
     */
    @PutMapping("/updateAccountStatus")
    public RestBean<String> updateAccountStatus(@Valid @RequestBody UpdateAccountStatusDTO dto) {
        adminService.updateAccountStatus(dto);
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

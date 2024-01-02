package com.ycb.controller;

import com.ycb.common.constant.StatusConstant;
import com.ycb.common.result.PageResult;
import com.ycb.common.result.RestBean;
import com.ycb.pojo.dto.PagePetDTO;
import com.ycb.pojo.vo.OnePetBulletinVO;
import com.ycb.service.PetService;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 获取宠物宠物布告控制器
 */
@RestController
@RequestMapping("/api/pet")
@Validated
public class PetController {
    @Resource
    private PetService petService;

    /**
     * 获取全部宠物信息
     *
     * @param dto 分页参数
     * @return 宠物列表
     */
    @GetMapping("/page")
    public RestBean<PageResult> page(PagePetDTO dto) {
        dto.setBulletinStatus(StatusConstant.REVIEW_PASS);
        PageResult pageResult = petService.page(dto);
        return RestBean.success(pageResult);
    }

    /**
     * 根据宠物id获取宠物信息
     *
     * @param petId 宠物id
     */
    @GetMapping("/getPBByPetId")
    public RestBean<OnePetBulletinVO> getPBByPetId(@NotBlank @Pattern(regexp = "^[0-9]+$", message = "用户id格式有误")
                                                   @RequestParam String petId) {
        OnePetBulletinVO pet = petService.getPBByPetId(Integer.valueOf(petId));
        return RestBean.success(pet);
    }

    /**
     * 获取关注的人发布的宠物信息
     *
     * @param dto 分页参数
     * @return 宠物列表
     */
    @GetMapping("/getFolloweePet")
    public RestBean<PageResult> getFolloweePet(PagePetDTO dto) {
        return this.page(dto);
    }
}

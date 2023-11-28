package com.ycb.controller;

import com.ycb.entity.RestBean;
import com.ycb.entity.dto.Pet;
import com.ycb.entity.vo.response.AllPetAndBulVO;
import com.ycb.entity.vo.response.OnePB2PicVO;
import com.ycb.service.PetService;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
     * @return 宠物列表
     */
    @GetMapping("/getAllPB")
    public RestBean<List<AllPetAndBulVO>> getAll() {
        List<AllPetAndBulVO> pets = petService.getAll();
        return RestBean.success(pets);
    }

    /**
     * 根据宠物id获取宠物信息
     *
     * @param petId 宠物id
     */
    @GetMapping("/getPBByPetId")
    public RestBean<OnePB2PicVO> getPBByPetId(@NotBlank @Pattern(regexp = "^[0-9]+$", message = "用户id格式有误")
                                                  @RequestParam String petId) {
        OnePB2PicVO pet = petService.getPBByPetId(Integer.valueOf(petId));
        return RestBean.success(pet);
    }

    /**
     * 根据宠物类型获取所有宠物
     *
     * @param type 宠物类型
     * @return 宠物列表
     */
    @GetMapping("/getAllByType")
    public RestBean<List<Pet>> getAllByType(@NotBlank @Pattern(regexp = "^(cat|dog|other)$", message = "宠物类型格式有误")
                                            @RequestParam String type) {
        List<Pet> pets = petService.getAllByType(type);
        return RestBean.success(pets);
    }
}

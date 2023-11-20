package com.ycb.controller;

import com.ycb.entity.RestBean;
import com.ycb.entity.dto.Pet;
import com.ycb.entity.vo.request.PublishBulletinVO;
import com.ycb.entity.vo.request.UpdateBulletinVO;
import com.ycb.entity.vo.response.AllPetAndBulVO;
import com.ycb.entity.vo.response.OnePB2PicVO;
import com.ycb.service.PetService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    /**
     * 发布领养信息：想领养adopt，求抱走away
     *
     * @return 发布结果
     */
    @PostMapping("/publishBulletin")
    public RestBean<String> publishBulletin(@Valid @RequestBody PublishBulletinVO vo) {
        String message = petService.publishBulletin(vo);
        return message == null ? RestBean.success("发布成功") : RestBean.failure(400, message);
    }

    /**
     * 根据宠物id修改宠物信息
     *
     * @param vo 宠物信息
     */
    @PutMapping("/updatePetByPetId")
    public RestBean<String> updatePetByPetId(@Valid @RequestBody UpdateBulletinVO vo) {
        String message = petService.updatePetByPetId(vo);
        return message == null ? RestBean.success("修改成功") : RestBean.failure(400, message);
    }
}

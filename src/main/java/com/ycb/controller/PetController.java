package com.ycb.controller;

import com.ycb.entity.RestBean;
import com.ycb.entity.dto.Pet;
import com.ycb.entity.vo.request.PublishBulletinVO;
import com.ycb.entity.vo.response.AllPetAndBulVO;
import com.ycb.entity.vo.response.OnePB2PicVO;
import com.ycb.service.PetService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pet")
public class PetController {
    @Resource
    private PetService petService;

    /**
     * 获取全部宠物信息
     * @return 宠物列表
     */
    @GetMapping("/getAllPB")
    public RestBean<List<AllPetAndBulVO>> getAll() {
        List<AllPetAndBulVO> pets = petService.getAll();
        return RestBean.success(pets);
    }

    /**
     * 根据宠物id获取宠物信息
     * @param petId 宠物id
     */
    @GetMapping("/getPBByPetId")
    public RestBean<OnePB2PicVO> getPBByPetId(@RequestParam Integer petId) {
        OnePB2PicVO pet = petService.getPBByPetId(petId);
        return RestBean.success(pet);
    }

    /**
     * 根据宠物类型获取所有宠物
     *
     * @param type 宠物类型
     * @return 宠物列表
     */
    @GetMapping("/getAllByType")
    public RestBean<List<Pet>> getAllByType(@RequestParam String type) {
        List<Pet> pets = petService.getAllByType(type);
        return RestBean.success(pets);
    }

    /**
     * 发布领养信息：想领养adopt，求抱走away
     *
     * @return 发布结果
     */
    @PostMapping("/publishBulletin")
    public RestBean<String> publishBulletin(@RequestBody PublishBulletinVO vo) {
        String message = petService.publishBulletin(vo);
        return message == null ? RestBean.success("发布成功") : RestBean.failure(400, message);
    }
}

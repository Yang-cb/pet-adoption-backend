package com.ycb.controller;

import com.ycb.entity.RestBean;
import com.ycb.entity.dto.Pet;
import com.ycb.entity.vo.request.PublishBulletinVO;
import com.ycb.service.PetService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pet")
public class PetController {
    @Resource
    private PetService petService;

    @GetMapping("/getAll")
    public RestBean<List<Pet>> getAll() {
        List<Pet> pets = petService.getAll();
        return RestBean.success(pets);
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
     * @return
     */
    @PostMapping("/publishBulletin")
    public RestBean<String> publishAdopt(@RequestBody PublishBulletinVO vo) {
        String message = petService.publishBulletin(vo);
        return message == null ? RestBean.success("发布成功") : RestBean.failure(400, message);
    }
}

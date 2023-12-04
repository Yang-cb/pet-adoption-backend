package com.ycb.controller;

import com.ycb.common.result.RestBean;
import com.ycb.pojo.dto.AccIdPetIdDTO;
import com.ycb.pojo.vo.AllPetAndBulVO;
import com.ycb.service.AccCollectBulService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户收藏宠物和布告的控制器
 */
@RestController
@RequestMapping("/api/collectBul")
@Validated
public class AccCollectBulController {
    @Resource
    private AccCollectBulService accCollectBulService;

    /**
     * 收藏/取消收藏宠物
     *
     * @param vo 用户id和宠物id
     * @return 收藏结果
     */
    @PostMapping("/collectPB")
    public RestBean<String> collectPB(@Valid @RequestBody AccIdPetIdDTO vo) {
        accCollectBulService.collectPB(vo);
        return RestBean.success();
    }

    /**
     * 获取用户收藏的宠物和布告
     *
     * @param id 用户id
     * @return 用户收藏的宠物和布告
     */
    @GetMapping("/getCollectPB")
    public RestBean<List<AllPetAndBulVO>> getCollectPB(@NotBlank @Pattern(regexp = "^[0-9]+$", message = "id格式有误")
                                                       @RequestParam String id) {
        List<AllPetAndBulVO> allPetAndBulVOS = accCollectBulService.getCollectPBById(Integer.valueOf(id));
        return RestBean.success(allPetAndBulVOS);
    }

    /**
     * 根据宠物id、宠物id判断用户是否收藏该宠物
     *
     * @param accId 用户id
     * @param petId 宠物id
     * @return 是否收藏
     */
    @GetMapping("/isCollect")
    public RestBean<Boolean> isCollect(@RequestParam Integer accId, @RequestParam Integer petId) {
        Boolean isCollect = accCollectBulService.isCollect(new AccIdPetIdDTO(accId, petId));
        return RestBean.success(isCollect);
    }
}

package com.ycb.controller;

import com.ycb.common.result.PageResult;
import com.ycb.common.result.RestBean;
import com.ycb.pojo.dto.AccIdPetIdDTO;
import com.ycb.pojo.dto.PageCollectPetDTO;
import com.ycb.service.CollectPetService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 用户收藏宠物和布告的控制器
 */
@RestController
@RequestMapping("/api/collectBul")
@Validated
public class CollectPetController {
    @Resource
    private CollectPetService collectPetService;

    /**
     * 收藏/取消收藏宠物
     *
     * @param vo 用户id和宠物id
     * @return 收藏结果
     */
    @PostMapping("/collectPB")
    public RestBean<String> collectPB(@Valid @RequestBody AccIdPetIdDTO vo) {
        collectPetService.collectPB(vo);
        return RestBean.success();
    }

    /**
     * 获取用户收藏的宠物和布告
     *
     * @param dto 分页信息
     * @return 用户收藏的宠物和布告
     */
    @GetMapping("/getCollectPB")
    public RestBean<PageResult> getCollectPB(PageCollectPetDTO dto) {
        PageResult pageResult = collectPetService.getAllCollect(dto);
        return RestBean.success(pageResult);
    }

    /**
     * 根据宠物id、宠物id判断用户是否收藏该宠物
     *
     * @param dto 用户id 宠物id
     * @return 是否收藏
     */
    @GetMapping("/isCollect")
    public RestBean<Boolean> isCollect(@Valid AccIdPetIdDTO dto) {
        Boolean isCollect = collectPetService.isCollect(dto);
        return RestBean.success(isCollect);
    }
}

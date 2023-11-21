package com.ycb.controller;

import com.ycb.entity.RestBean;
import com.ycb.entity.vo.request.AccIdPetIdVO;
import com.ycb.entity.vo.response.AllPetAndBulVO;
import com.ycb.service.Acc2PicService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/acc2pic")
@Validated
public class Acc2PicController {
    @Resource
    private Acc2PicService acc2PicService;

    /**
     * 收藏/取消收藏宠物
     *
     * @param vo 用户id和宠物id
     * @return 收藏结果
     */
    @PostMapping("/collectPB")
    public RestBean<String> collectPB(@Valid @RequestBody AccIdPetIdVO vo) {
        String message = acc2PicService.collectPB(vo);
        return message == null ? RestBean.success("操作成功") : RestBean.failure(402, message);
    }

    /**
     * 获取用户发布的宠物和布告
     *
     * @param id 用户id
     * @return 用户发布的宠物和布告
     */
    @GetMapping("/getPostPB")
    public RestBean<List<AllPetAndBulVO>> getPostPB(@NotBlank @Pattern(regexp = "^[0-9]+$", message = "id格式有误")
                                                    @RequestParam String id) {
        List<AllPetAndBulVO> allPetAndBulVOS = acc2PicService.getPostPBById(Integer.valueOf(id));
        return RestBean.success(allPetAndBulVOS);
    }

    /**
     * 根据宠物id逻辑删除用户发布的宠物和布告
     *
     * @return 删除结果
     */
    @PostMapping("/deletePostPB")
    public RestBean<String> updatePostPBIsDeleteByPetId(@Valid @RequestBody AccIdPetIdVO vo) {
        String message = acc2PicService.updatePostPBIsDelete(vo);
        return message == null ? RestBean.success("删除成功") : RestBean.failure(401, message);
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
        List<AllPetAndBulVO> allPetAndBulVOS = acc2PicService.getCollectPBById(Integer.valueOf(id));
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
        Boolean isCollect = acc2PicService.isCollect(new AccIdPetIdVO(accId, petId));
        return RestBean.success(isCollect);
    }
}

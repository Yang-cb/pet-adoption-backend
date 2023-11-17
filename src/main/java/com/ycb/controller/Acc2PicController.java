package com.ycb.controller;

import com.ycb.entity.RestBean;
import com.ycb.entity.dto.CollectAccPet;
import com.ycb.entity.vo.response.AllPetAndBulVO;
import com.ycb.service.Acc2PicService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/acc2pic")
public class Acc2PicController {
    @Resource
    private Acc2PicService acc2PicService;

    /**
     * 收藏宠物
     *
     * @param collectAccPet 用户id和宠物id
     * @return 收藏结果
     */
    @PostMapping("/collectPB")
    public RestBean<String> collectPB(@RequestBody CollectAccPet collectAccPet) {
        String message = acc2PicService.collectPB(collectAccPet);
        return message == null ? RestBean.success("收藏成功") : RestBean.failure(402, message);
    }

    /**
     * 取消收藏宠物
     *
     * @param collectAccPet 用户id和宠物id
     * @return 取消收藏结果
     */
    @PostMapping("/cancelCollectPB")
    public RestBean<String> cancelCollectPB(@RequestBody CollectAccPet collectAccPet) {
        String message = acc2PicService.cancelCollectPB(collectAccPet);
        return message == null ? RestBean.success("取消收藏成功") : RestBean.failure(403, message);
    }

    /**
     * 获取用户发布的宠物和布告
     *
     * @param id 用户id
     * @return 用户发布的宠物和布告
     */
    @GetMapping("/getPostPB")
    public RestBean<List<AllPetAndBulVO>> getPostPB(@RequestParam Integer id) {
        List<AllPetAndBulVO> allPetAndBulVOS = acc2PicService.getPostPBById(id);
        return RestBean.success(allPetAndBulVOS);
    }

    /**
     * 根据宠物id逻辑删除用户发布的宠物和布告
     * @param petId 宠物id
     * @return 删除结果
     */
    @PostMapping("/deletePostPB")
    public RestBean<String> updatePostPBIsDeleteByPetId(@RequestBody CollectAccPet collectAccPet){
        String message = acc2PicService.updatePostPBIsDeleteByPetId(collectAccPet.getPetId());
        return message == null ? RestBean.success("删除成功") : RestBean.failure(401, message);
    }
}

package com.ycb.controller;

import com.ycb.common.result.RestBean;
import com.ycb.pojo.dto.AccIdPetIdDTO;
import com.ycb.pojo.dto.PublishBulletinDTO;
import com.ycb.pojo.dto.UpdateBulletinDTO;
import com.ycb.pojo.vo.AllPetAndBulVO;
import com.ycb.service.AccPostBulService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户发布宠物和布告的控制器
 */
@RestController
@RequestMapping("/api/postBul")
public class AccPostBulController {
    @Resource
    private AccPostBulService accPostBulService;

    /**
     * 发布领养信息：想领养adopt，求抱走away
     *
     * @return 发布结果
     */
    @PostMapping("/publishBulletin")
    public RestBean<String> publishBulletin(@Valid PublishBulletinDTO vo) {
        accPostBulService.publishBulletin(vo);
        return RestBean.success();
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
        List<AllPetAndBulVO> allPetAndBulVOS = accPostBulService.getPostPBById(Integer.valueOf(id));
        return RestBean.success(allPetAndBulVOS);
    }

    /**
     * 根据宠物id逻辑删除用户发布的宠物和布告
     *
     * @param vo 用户id 宠物id
     * @return 删除结果
     */
    @PostMapping("/deletePostPB")
    public RestBean<String> updatePostPBIsDeleteByPetId(@Valid @RequestBody AccIdPetIdDTO vo) {
        accPostBulService.updatePostPBIsDelete(vo);
        return RestBean.success();
    }

    /**
     * 根据宠物id修改用户发布的宠物布告信息：可选择是否上传图片，如不上传，将使用原图片
     *
     * @param vo 宠物信息
     */
    @PutMapping("/updatePetByPetId")
    public RestBean<String> updatePetByPetId(@Valid UpdateBulletinDTO vo) {
        accPostBulService.updatePetByPetId(vo);
        return RestBean.success();
    }
}

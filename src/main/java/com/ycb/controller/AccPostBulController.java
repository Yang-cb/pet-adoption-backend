package com.ycb.controller;

import com.ycb.entity.RestBean;
import com.ycb.entity.vo.request.AccIdPetIdVO;
import com.ycb.entity.vo.request.PublishBulletinVO;
import com.ycb.entity.vo.request.UpdateBulletinVO;
import com.ycb.entity.vo.response.AllPetAndBulVO;
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
    public RestBean<String> publishBulletin(@Valid PublishBulletinVO vo) {
        String message = accPostBulService.publishBulletin(vo);
        return message == null ? RestBean.success("发布成功") : RestBean.failure(400, message);
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
    public RestBean<String> updatePostPBIsDeleteByPetId(@Valid @RequestBody AccIdPetIdVO vo) {
        String message = accPostBulService.updatePostPBIsDelete(vo);
        return message == null ? RestBean.success("删除成功") : RestBean.failure(401, message);
    }

    /**
     * 根据宠物id修改用户发布的宠物布告信息
     *
     * @param vo 宠物信息
     */
    @PutMapping("/updatePetByPetId")
    public RestBean<String> updatePetByPetId(@Valid @RequestBody UpdateBulletinVO vo) {
        String message = accPostBulService.updatePetByPetId(vo);
        return message == null ? RestBean.success("修改成功") : RestBean.failure(400, message);
    }
}

package com.ycb.controller;

import com.ycb.common.result.PageResult;
import com.ycb.common.result.RestBean;
import com.ycb.pojo.dto.AccIdPetIdDTO;
import com.ycb.pojo.dto.AddBulletinDTO;
import com.ycb.pojo.dto.PagePostPetDTO;
import com.ycb.pojo.dto.UpdateBulletinDTO;
import com.ycb.service.PostPetService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;


/**
 * 用户发布宠物和布告的控制器
 */
@RestController
@RequestMapping("/api/postBul")
public class PostPetController {
    @Resource
    private PostPetService postPetService;

    /**
     * 发布领养信息：想领养adopt，求抱走away
     *
     * @return 发布结果
     */
    @PostMapping("/publishBulletin")
    public RestBean<String> publishBulletin(@Valid AddBulletinDTO vo) {
        postPetService.publishBulletin(vo);
        return RestBean.success();
    }

    /**
     * 获取用户发布的宠物和布告
     *
     * @param dto 分页发布的宠物数据传输对象
     * @return 用户发布的宠物和布告
     */
    @GetMapping("/getPostPB")
    public RestBean<PageResult> getPostPB(PagePostPetDTO dto) {
        PageResult page = postPetService.getPostPBById(dto);
        return RestBean.success(page);
    }

    /**
     * 根据宠物id逻辑删除用户发布的宠物和布告
     *
     * @param vo 用户id 宠物id
     * @return 删除结果
     */
    @PostMapping("/deletePostPB")
    public RestBean<String> deletePostPB(@Valid @RequestBody AccIdPetIdDTO vo) {
        postPetService.deletePostPB(vo);
        return RestBean.success();
    }

    /**
     * 根据宠物id修改用户发布的宠物布告信息：可选择是否上传图片，如不上传，将使用原图片
     *
     * @param vo 宠物信息
     */
    @PutMapping("/updatePetByPetId")
    public RestBean<String> updatePetByPetId(@Valid UpdateBulletinDTO vo) {
        postPetService.updatePetByPetId(vo);
        return RestBean.success();
    }
}

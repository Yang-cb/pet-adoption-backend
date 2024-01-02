package com.ycb.controller;

import com.ycb.common.result.PageResult;
import com.ycb.common.result.RestBean;
import com.ycb.pojo.dto.FerIdFedIdDTO;
import com.ycb.pojo.dto.PageFollowDTO;
import com.ycb.service.FollowService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 * 关注控制器
 */
@RestController
@RequestMapping("/api/follow")
public class FollowController {
    @Resource
    private FollowService followService;

    /**
     * 关注/取消关注
     *
     * @param dto 添加关注的数据传输对象
     * @return 添加关注的结果
     */
    @PostMapping("/followAuthor")
    public RestBean<String> addFollow(@Valid @RequestBody FerIdFedIdDTO dto) {
        followService.followAuthor(dto);
        return RestBean.success();
    }

    /**
     * 判断是否关注
     *
     * @param dto 关注的数据传输对象
     * @return 是否关注
     */
    @GetMapping("/isFollowed")
    public RestBean<Boolean> isFollowed(@Valid FerIdFedIdDTO dto) {
        return RestBean.success(followService.isFollowed(dto));
    }

    /**
     * 获取关注数
     *
     * @param accountId 用户id
     * @return 关注数
     */
    @GetMapping("/getFollowedNum")
    public RestBean<Integer> getFollowNum(Integer accountId) {
        return RestBean.success(followService.getFollowNumByAccountId(accountId));
    }

    /**
     * 获取关注列表
     *
     * @param dto 关注列表的数据传输对象
     * @return 关注列表
     */
    @GetMapping("/getFollowedList")
    public RestBean<PageResult> getFollowedList(PageFollowDTO dto) {
        return RestBean.success(followService.getFollowedList(dto));
    }
}

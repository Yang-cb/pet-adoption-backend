package com.ycb.controller;

import com.ycb.common.result.PageResult;
import com.ycb.common.result.RestBean;
import com.ycb.pojo.dto.AddReviewDTO;
import com.ycb.pojo.dto.DeleteReviewDTO;
import com.ycb.pojo.dto.PageReviewDTO;
import com.ycb.service.ReviewService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 * 评论控制器
 */
@RestController
@RequestMapping("/api/review")
public class ReviewController {
    @Resource
    private ReviewService reviewService;

    /**
     * 添加评论
     *
     * @param dto 评论信息
     * @return 成功与否
     */
    @PostMapping("/addReview")
    public RestBean<String> addReview(@Valid @RequestBody AddReviewDTO dto) {
        reviewService.addReview(dto);
        return RestBean.success();
    }


    /**
     * 根据宠物id获取评论
     *
     * @param dto 分页信息
     * @return 评论列表
     */
    @GetMapping("/getReview")
    public RestBean<PageResult> getReviewByPetId(PageReviewDTO dto) {
        PageResult pageResult = reviewService.getReviewByPetId(dto);
        return RestBean.success(pageResult);
    }

    /**
     * 删除评论
     *
     * @param dto 账户id和评论id
     * @return 成功与否
     */
    @PutMapping("/deleteReview")
    public RestBean<String> deleteReview(@Valid @RequestBody DeleteReviewDTO dto) {
        reviewService.deleteReview(dto);
        return RestBean.success();
    }
}

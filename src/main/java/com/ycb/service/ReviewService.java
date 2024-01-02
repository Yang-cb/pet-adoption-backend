package com.ycb.service;

import com.ycb.common.result.PageResult;
import com.ycb.pojo.dto.AddReviewDTO;
import com.ycb.pojo.dto.DeleteReviewDTO;
import com.ycb.pojo.dto.PageReviewDTO;

public interface ReviewService {
    /**
     * 添加评论
     *
     * @param dto 评论信息
     */
    void addReview(AddReviewDTO dto);

    /**
     * 根据宠物id获取评论
     *
     * @param dto 分页信息
     * @return 评论列表
     */
    PageResult getReviewByPetId(PageReviewDTO dto);

    /**
     * 删除评论
     *
     * @param dto 账户id和评论id
     */
    void deleteReview(DeleteReviewDTO dto);
}

package com.ycb.mapper;

import com.github.pagehelper.Page;
import com.ycb.annotation.AutoFill;
import com.ycb.common.enumerate.OperationType;
import com.ycb.pojo.dto.DeleteReviewDTO;
import com.ycb.pojo.dto.PageReviewDTO;
import com.ycb.pojo.entity.Review;
import com.ycb.pojo.vo.AllReviewVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReviewMapper {
    /**
     * 添加评论
     *
     * @param dto 评论信息
     */
    @AutoFill(OperationType.INSERT)
    void addReview(Review dto);

    /**
     * 根据宠物id获取评论
     *
     * @param dto 分页信息
     * @return 评论列表
     */
    Page<AllReviewVO> getReviewByPetId(PageReviewDTO dto);


    /**
     * 根据评论id和账户id获取评论
     *
     * @param dto 评论id和账户id
     * @return 评论数量
     */
    int getReviewByReviewIdAndAccountId(DeleteReviewDTO dto);

    /**
     * 删除评论
     *
     * @param dto 账户id和评论id
     */
    @AutoFill(OperationType.DELETE)
    void deleteReview(DeleteReviewDTO dto);
}

package com.ycb.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ycb.common.result.PageResult;
import com.ycb.exception.OperationException;
import com.ycb.mapper.ReviewMapper;
import com.ycb.pojo.dto.AddReviewDTO;
import com.ycb.pojo.dto.DeleteReviewDTO;
import com.ycb.pojo.dto.PageReviewDTO;
import com.ycb.pojo.entity.Review;
import com.ycb.pojo.vo.AllReviewVO;
import com.ycb.service.ReviewService;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Resource
    private ReviewMapper reviewMapper;

    @Override
    public void addReview(AddReviewDTO dto) {
        Review review = new Review();
        BeanUtils.copyProperties(dto, review);
        reviewMapper.addReview(review);
    }

    @Override
    public PageResult getReviewByPetId(PageReviewDTO dto) {
        PageHelper.startPage(dto.getPage(), dto.getPageSize());
        Page<AllReviewVO> page = reviewMapper.getReviewByPetId(dto);
        return PageResult.builder()
                .records(page.getResult())
                .total(page.getTotal())
                .build();
    }

    @Override
    public void deleteReview(DeleteReviewDTO dto) {
        int review = reviewMapper.getReviewByReviewIdAndAccountId(dto);
        if (review == 0) {
            throw new OperationException();
        }
        reviewMapper.deleteReview(dto);
    }
}

package com.ycb.pojo.dto;

import lombok.Data;

@Data
public class PageReviewDTO {
    /**
     * 页码
     */
    private Integer page;
    /**
     * 每页显示条数
     */
    private Integer pageSize;
    /**
     * 宠物id
     */
    private Integer petId;
}
package com.ycb.pojo.dto;

import lombok.Data;

/**
 * 分页收藏数据传输对象
 */
@Data
public class PageCollectPetDTO {
    /**
     * 页码
     */
    private Integer page;
    /**
     * 每页显示条数
     */
    private Integer pageSize;
    /**
     * 用户id
     */
    private Integer accountId;
}
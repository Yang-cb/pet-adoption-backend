package com.ycb.pojo.dto;

import lombok.Data;

/**
 * 分页发布的宠物数据传输对象
 */
@Data
public class PagePostPetDTO {
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
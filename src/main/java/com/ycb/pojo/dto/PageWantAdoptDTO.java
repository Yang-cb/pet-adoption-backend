package com.ycb.pojo.dto;

import lombok.Data;

/**
 * 分页查询想领数据传输对象
 */
@Data
public class PageWantAdoptDTO {
    /**
     * 页码
     */
    private Integer page;
    /**
     * 每页显示条数
     */
    private Integer pageSize;
    /**
     * 宠物类型
     */
    private String petType;
    /**
     * 想领状态
     */
    private Integer wantStatus;
    /**
     * 用户id
     */
    private Integer accountId;
}

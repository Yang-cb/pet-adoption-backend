package com.ycb.pojo.dto;

import lombok.Data;

@Data
public class PageFollowDTO {
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

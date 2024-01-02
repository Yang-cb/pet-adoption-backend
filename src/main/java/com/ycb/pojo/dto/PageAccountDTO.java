package com.ycb.pojo.dto;

import lombok.Data;

@Data
public class PageAccountDTO {
    /**
     * 页码
     */
    private Integer page;
    /**
     * 每页显示条数
     */
    private Integer pageSize;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户状态
     */
    private Integer accountStatus;
}

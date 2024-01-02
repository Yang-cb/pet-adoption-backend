package com.ycb.pojo.dto;

import lombok.Data;

@Data
public class PagePetDTO {
    /**
     * 文章标题
     */
    private String title;
    /**
     * 宠物类型
     */
    private String petType;
    /**
     * 布告状态
     */
    private Integer bulletinStatus;
    /**
     * 是否免费的字符串表示
     */
    private String isFreeStr;
    /**
     * 是否免费
     */
    private Integer isFree;
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

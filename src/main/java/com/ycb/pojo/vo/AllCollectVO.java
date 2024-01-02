package com.ycb.pojo.vo;

import lombok.Data;

@Data
public class AllCollectVO {
    /**
     * 宠物id
     */
    private Integer petId;
    /**
     * 文章标题
     */
    private String title;
    /**
     * 发布人id
     */
    private Integer accountId;
    /**
     * 发布人昵称
     */
    private String nikeName;
    /**
     * 宠物照片
     */
    private String petPicName;
    /**
     * 发布人头像
     */
    private String authorPicName;
}

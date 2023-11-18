package com.ycb.entity.dto;

import lombok.Data;

/**
 * 收藏账号宠物关联表
 */
@Data
public class CollectAccPet {
    /**
     * 自增id
     */
    private Integer id;
    /**
     * 用户id
     */
    private Integer accId;
    /**
     * 宠物id
     */
    private Integer petId;
}

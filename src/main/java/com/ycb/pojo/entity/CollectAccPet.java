package com.ycb.pojo.entity;

import lombok.Data;

import java.time.LocalDateTime;

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
    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;
    /**
     * 修改时间
     */
    private LocalDateTime gmtModified;
    /**
     * 是否删除 1是0否
     */
    private Integer isDelete;
}

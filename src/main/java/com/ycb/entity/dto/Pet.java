package com.ycb.entity.dto;

import lombok.Data;

import java.sql.Date;

/**
 * 宠物
 */
@Data
public class Pet {
    private Integer id;
    private String petName;
    private String petType;
    /**
     * 性别 1公0母
     */
    private Integer sex;
    /**
     * 是否免费
     */
    private Integer isFree;
    /**
     * 照片
     */
    private String picture;
    /**
     * 领养地址
     */
    private String location;
    /**
     * 是否已被领养 0是1否
     */
    private Integer isAdopt;
    /**
     * 登记时间
     */
    private Date gmtCreate;
    /**
     * 修改时间
     */
    private Date gmtModified;
    /**
     * 是否删除 1是0否
     */
    private Integer isDelete;
    /**
     * 布告id
     */
    private Integer bulletinId;
}

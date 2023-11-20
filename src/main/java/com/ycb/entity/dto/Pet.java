package com.ycb.entity.dto;

import lombok.Data;

import java.sql.Date;

/**
 * 宠物
 */
@Data
public class Pet {
    /**
     * 自增id
     */
    private Integer petId;
    /**
     * 布告id
     */
    private Integer bulletinId;
    /**
     * 宠物姓名
     */
    private String petName;
    /**
     * 宠物类型：cat猫、dog狗、other其他
     */
    private String petType;
    /**
     * 性别 1公0母
     */
    private Integer petSex;
    /**
     * 是否免费
     */
    private Integer isFree;
    /**
     * 照片ID
     */
    private String pictureId;
    /**
     * 是否已被领养 1是0否
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
}

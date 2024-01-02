package com.ycb.pojo.entity;

import lombok.Data;

import java.time.LocalDateTime;

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
     * 是否绝育
     */
    private Integer isSterilization;
    /**
     * 健康状态
     */
    private String petHealthStatus;
    /**
     * 照片ID
     */
    private Integer pictureId;
    /**
     * 是否已被领养 1是0否
     */
    private Integer isAdopt;
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

package com.ycb.entity.vo.response;

import lombok.Data;

import java.sql.Date;

@Data
public class PetAndBulVO {
    /**
     * 宠物姓名
     */
    private String petName;
    /**
     * 宠物种类
     */
    private String petType;
    /**
     * 性别 1公0母
     */
    private Integer sex;
    /**
     * 是否免费 1是0否
     */
    private Integer isFree;
    /**
     * 宠物照片
     */
    private byte[] picData;
    /**
     * 宠物生日
     */
    private String birthdate;
    /**
     * 领养人地点
     */
    private String location;
    /**
     * 文章
     */
    private String title;
    private String text;
    /**
     * 发布布告的最后时间
     */
    private Date gmtModified;
}

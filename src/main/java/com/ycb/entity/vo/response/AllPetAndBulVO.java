package com.ycb.entity.vo.response;

import lombok.Data;

import java.sql.Date;

/**
 * 宠物信息及布告信息
 */
@Data
public class AllPetAndBulVO {
    /**
     * 宠物id
     */
    private Integer petId;
    /**
     * 宠物姓名
     */
    private String petName;
    /**
     * 宠物种类
     */
    private String petType;
    /**
     * 宠物性别 1公0母
     */
    private Integer petSex;
    /**
     * 是否免费 1是0否
     */
    private Integer isFree;
    /**
     * 宠物照片名字
     */
    private String picName;
    /**
     * 领养人地点
     */
    private String location;
    /**
     * 联系人
     */
    private String contactsName;
    /**
     * 手机号
     */
    private String contactsPhone;
    /**
     * 微信
     */
    private String contactsWechat;
    /**
     * 邮箱
     */
    private String contactsEmail;
    /**
     * 文章标题
     */
    private String title;
    /**
     * 文章内容
     */
    private String text;
    /**
     * 发布布告的时间
     */
    private Date gmtCreate;
}

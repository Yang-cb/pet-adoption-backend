package com.ycb.entity.vo.request;

import lombok.Data;

@Data
public class PublishBulletinVO {
    /**
     * 请求类型：想领养，求抱走
     */
    private String type;
    /**
     * 宠物
     */
    private String petName;
    private String petType;
    /**
     * 宠物照片id
     */
    private String pictureId;
    /**
     * 性别 1公0母
     */
    private Integer sex;
    /**
     * 是否免费 1是0否
     */
    private Integer isFree;
    private String birthdate;
    /**
     * 发布人
     */
    private String location;
    private String contactsName;
    private String contactsPhone;
    private String contactsWechat;
    private String contactsEmail;
    /**
     * 文章
     */
    private String title;
    private String text;
}
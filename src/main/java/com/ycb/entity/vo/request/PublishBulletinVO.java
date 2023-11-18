package com.ycb.entity.vo.request;

import lombok.Data;

/**
 * 发布公告请求封装对象
 */
@Data
public class PublishBulletinVO {
    /**
     * 发布者id
     */
    private Integer accountId;
    /**
     * 请求类型：想领养，求抱走
     */
    private String type;
    /**
     * 宠物姓名
     */
    private String petName;
    /**
     * 宠物类型
     */
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
    /**
     * 领养地址
     */
    private String location;
    /**
     * 联系人
     */
    private String contactsName;
    /**
     * 联系方式
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
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String text;
}
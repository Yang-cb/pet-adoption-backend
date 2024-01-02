package com.ycb.pojo.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 管理员获取全部宠物信息
 */
@Data
public class AllPetBulletinVO {
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
     * 是否已被领养
     */
    private Integer isAdopt;
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
    private LocalDateTime gmtCreate;
    /**
     * 更新布告的时间
     */
    private LocalDateTime gmtModified;
    /**
     * 布告状态 1正常0删除
     */
    private Integer isDelete;
    /**
     * 发布人id
     */
    private Integer accountId;
    /**
     * 布告状态
     */
    private Integer bulletinStatus;
    /**
     * 是否绝育
     */
    private Integer isSterilization;
    /**
     * 健康状态
     */
    private String petHealthStatus;
}

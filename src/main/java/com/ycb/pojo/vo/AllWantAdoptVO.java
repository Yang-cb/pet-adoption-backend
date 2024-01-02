package com.ycb.pojo.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 获取所有想要领养信息
 */
@Data
public class AllWantAdoptVO {
    /**
     * 自增id
     */
    private Integer wantId;
    /**
     * 发布想要领养用户id
     */
    private Integer accountId;
    /**
     * 宠物id
     */
    private Integer petId;
    /**
     * 状态 0未审核；1审核通过；2审核未通过；3同意；4拒绝；
     */
    private Integer wantStatus;
    /**
     * 联系人
     */
    private String wantContactsName;
    /**
     * 联系人地点
     */
    private String wantContactsLocation;
    /**
     * 联系人手机号
     */
    private String wantContactsPhone;
    /**
     * 联系人邮箱
     */
    private String wantContactsEmail;
    /**
     * 联系人微信
     */
    private String wantContactsWechat;
    /**
     * 领养条件
     */
    private String wantText;
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
     * 宠物图片
     */
    private String petPicName;
    /**
     * 用户图片
     */
    private String accPicName;
    /**
     * 文章内容
     */
    private String text;
    /**
     * 文章标题
     */
    private String title;
    /**
     * 是否已被领养 1是0否
     */
    private Integer isAdopt;
    /**
     * 发布时间
     */
    private LocalDateTime gmtCreate;
    /**
     * 更新时间
     */
    private LocalDateTime gmtModified;
}

package com.ycb.pojo.entity;

import lombok.Data;

import java.sql.Date;

/**
 * 布告
 */
@Data
public class Bulletin {
    /**
     * 自增id
     */
    private Integer bulletinId;
    /**
     * 发这个布告的用户id
     */
    private Integer accountId;
    /**
     * 布告类型：领养/寻宠
     */
    private String type;
    /**
     * 布告状态
     */
    private Integer bulletinStatus;
    /**
     * 联系人
     */
    private String contactsName;
    /**
     * 联系人手机号
     */
    private String contactsPhone;
    /**
     * 联系人微信
     */
    private String contactsWechat;
    /**
     * 联系人邮箱
     */
    private String contactsEmail;
    /**
     * 领养人地址
     */
    private String location;
    /**
     * 文章标题
     */
    private String title;
    /**
     * 文章内容
     */
    private String text;
    /**
     * 创建时间
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

package com.ycb.entity.dto;

import lombok.Data;

import java.sql.Date;

/**
 * 想要领养信息表
 */
@Data
public class WantAdopt {
    /**
     * 自增id
     */
    private Integer wantId;
    /**
     * 用户id
     */
    private Integer accountId;
    /**
     * 宠物id
     */
    private Integer petId;
    /**
     * 联系人
     */
    private String contactsName;
    /**
     * 联系人地点
     */
    private String contactsLocation;
    /**
     * 联系人手机号
     */
    private String contactsPhone;
    /**
     * 联系人邮箱
     */
    private String contactsEmail;
    /**
     * 联系人微信
     */
    private String contactsWechat;
    /**
     * 领养条件
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

package com.ycb.pojo.entity;

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

package com.ycb.pojo.vo.admin;

import lombok.Data;

import java.sql.Date;

@Data
public class AdminAllWantAdoptVO {
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
     * 发布时间
     */
    private Date gmtCreate;
    /**
     * 更新时间
     */
    private Date gmtModified;
}

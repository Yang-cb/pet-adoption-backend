package com.ycb.entity.dto;

import lombok.Data;

import java.sql.Date;

/**
 * 布告
 */
@Data
public class Bulletin {
    private Integer id;
    private String type;
    /**
     * 联系人
     */
    private String contactsName;
    private String contactsPhone;
    private String contactsWechat;
    private String contactsEmail;
    /**
     * 领养人地址
     */
    private String location;
    /**
     * 文章
     */
    private String title;
    private String text;
    private Date gmtCreate;
    private Date gmtModified;
    private Integer isDelete;
    /**
     * 用户id
     */
    private Integer accountId;
}

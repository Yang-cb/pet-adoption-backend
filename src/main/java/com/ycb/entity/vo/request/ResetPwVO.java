package com.ycb.entity.vo.request;

import lombok.Data;

import java.sql.Date;

/**
 * 重置密码封装对象
 */
@Data
public class ResetPwVO {
    /**
     * 邮箱
     */
    private String email;
    /**
     * 验证码
     */
    private String code;
    /**
     * 密码
     */
    private String password;
    /**
     * 重置时间
     */
    private Date gmtModified;
}

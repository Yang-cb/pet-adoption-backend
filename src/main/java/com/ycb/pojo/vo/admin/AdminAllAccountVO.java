package com.ycb.pojo.vo.admin;

import lombok.Data;

import java.sql.Date;

/**
 * 管理员获取全部用户信息
 */
@Data
public class AdminAllAccountVO {
    /**
     * 用户id
     */
    private Integer accountId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 权限
     */
    private String authority;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 地址
     */
    private String location;
    /**
     * 昵称
     */
    private String nikeName;
    /**
     * 出生日期
     */
    private Date birthday;
    /**
     * 性别
     */
    private Integer sex;
    /**
     * 签名
     */
    private String signature;
    /**
     * 创建日期
     */
    private Date gmtCreate;
    /**
     * 更新日期
     */
    private Date gmtModified;
    /**
     * 状态
     */
    private Integer AccountStatus;
    /**
     * 是否删除
     */
    private Integer isDelete;
    /**
     * 头像
     */
    private String picName;
}

package com.ycb.common.constant;

/**
 * Redis键常量类
 */
public class RedisConstant {
    /**
     * 验证码存入redis时间
     */
    public static final int VERIFICATION_CODE_EXPIRE_TIME = 3;
    /**
     * 频繁请求时间
     */
    public static final int FREQUENT_REQUEST_EXPIRE_TIME = 1;
    /**
     * 退出登录jwt黑名单
     */
    public static final String LOGOUT_JWT_BLACK_LIST = "logout:jwt:black:list:";
    /**
     * 频繁请求发送邮件
     */
    public static final String SEND_MAIL_FREQUENT = "send:mail:frequent:";
    /**
     * 接受邮件的邮箱地址
     */
    public static final String RECEIVE_MAIL = "receive:mail:";
}

package com.ycb.exception;

/**
 * 邮箱相关异常
 */
public class EmailException extends BaseException {
    public EmailException(Integer code, String msg) {
        super(code, msg);
    }
}

package com.ycb.exception;

/**
 * 注册相关异常
 */
public class RegisterException extends BaseException {
    public RegisterException(Integer code, String msg) {
        super(code, msg);
    }
}

package com.ycb.exception;

/**
 * 验证码相关异常
 */
public class VerificationCodeException extends BaseException {
    public VerificationCodeException(Integer code, String msg) {
        super(code, msg);
    }
}

package com.ycb.exception;

public class ResetPasswordException extends BaseException {
    public ResetPasswordException(Integer code, String msg) {
        super(code, msg);
    }
}

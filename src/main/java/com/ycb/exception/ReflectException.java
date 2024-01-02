package com.ycb.exception;

import org.springframework.http.HttpStatus;

/**
 * 反射异常
 */
public class ReflectException extends BaseException {
    public ReflectException(String message) {
        super(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
    }
}

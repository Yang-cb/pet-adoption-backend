package com.ycb.exception;

import lombok.Getter;

import java.io.Serial;

/**
 * 基础业务异常
 */
@Getter
public class BaseException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * http状态码
     */
    private Integer code;
    /**
     * 异常信息
     */
    private String msg;

    public BaseException() {
    }

    public BaseException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }
}

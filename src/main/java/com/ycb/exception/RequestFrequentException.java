package com.ycb.exception;

/**
 * 请求频繁异常
 */
public class RequestFrequentException extends BaseException {
    public RequestFrequentException(Integer code, String msg) {
        super(code, msg);
    }
}

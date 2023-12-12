package com.ycb.exception;

import com.ycb.common.constant.MessageConstant;
import org.springframework.http.HttpStatus;

/**
 * 请求频繁异常
 */
public class RequestFrequentException extends BaseException {
    public RequestFrequentException() {
        super(HttpStatus.TOO_MANY_REQUESTS.value(), MessageConstant.REQUEST_FREQUENT);
    }
}

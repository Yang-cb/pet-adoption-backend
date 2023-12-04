package com.ycb.exception;

import com.ycb.constant.MessageConstant;
import org.springframework.http.HttpStatus;

/**
 * 系统异常
 */
public class SystemException extends BaseException {
    public SystemException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR.value(), MessageConstant.SYSTEM_ERROR);
    }
}

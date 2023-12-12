package com.ycb.exception;

import com.ycb.common.constant.MessageConstant;
import org.springframework.http.HttpStatus;

/**
 * 操作异常
 */
public class OperationException extends BaseException {
    public OperationException(Integer code, String msg) {
        super(code, msg);
    }

    public OperationException() {
        super(HttpStatus.BAD_REQUEST.value(), MessageConstant.OPERATION_FAILED);
    }
}

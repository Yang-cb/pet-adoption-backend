package com.ycb.exception;

import com.ycb.constant.MessageConstant;
import org.springframework.http.HttpStatus;

/**
 * 文件异常
 */
public class FileException extends BaseException {
    public FileException(Integer code, String msg) {
        super(code, msg);
    }

    public FileException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR.value(), MessageConstant.FILE_UPLOAD_FAILED);
    }
}

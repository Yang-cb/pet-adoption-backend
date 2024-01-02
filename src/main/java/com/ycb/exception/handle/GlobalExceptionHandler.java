package com.ycb.exception.handle;

import com.ycb.common.constant.MessageConstant;
import com.ycb.common.result.RestBean;
import com.ycb.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器，处理抛出的自定义异常
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 捕获自定义异常
     *
     * @param e 异常：全部继承于 BaseException
     * @return 响应结果
     */
    @ExceptionHandler(BaseException.class)
    public RestBean<String> exceptionHandler(BaseException e) {
        log.error(e.getClass() + " - 异常信息：{}", e.getMessage());
        return RestBean.failure(e.getCode(), e.getMessage());
    }

    /**
     * 捕获数据库异常
     *
     * @param e 异常
     * @return 响应结果
     */
    @ExceptionHandler(DataAccessException.class)
    public RestBean<String> exceptionHandler(DataAccessException e) {
        log.error(e.getClass() + " - 异常信息：{}", e.getCause().getMessage());
        return RestBean.failure(HttpStatus.INTERNAL_SERVER_ERROR.value(), MessageConstant.DATABASE_ERROR);
    }
}
package com.ycb.exception;

import com.ycb.entity.RestBean;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用于定义统一的Validated校验异常处理类，而不是在每个Controller中逐个定义
 */
@RestControllerAdvice
public class ValidationExceptionHandle {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class, ValidationException.class})
    public RestBean<Map<String, String>> handleValidationExceptions(
            Exception ex) {
        // 错误未知
        Map<String, String> errors = new HashMap<>();
        // 错误信息
        List<String> message = new ArrayList<>();
        if (ex instanceof MethodArgumentNotValidException) {
            ((MethodArgumentNotValidException) ex).getBindingResult().getAllErrors().forEach((error) -> {
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                errors.put(fieldName, errorMessage);
                message.add(errorMessage);
            });
        } else if (ex instanceof BindException) {
            ((BindException) ex).getBindingResult().getAllErrors().forEach((error) -> {
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                errors.put(fieldName, errorMessage);
                message.add(errorMessage);
            });
        } else if (ex instanceof ConstraintViolationException) {
            ((ConstraintViolationException) ex).getConstraintViolations().forEach((error) -> {
                String fieldName = error.getPropertyPath().toString();
                String errorMessage = error.getMessage();
                errors.put(fieldName, errorMessage);
                message.add(errorMessage);
            });
        } else {
            // 其他ValidationException子类
            errors.put("exception", "参数异常");
            message.add("参数异常");
        }
        return RestBean.failure(400, errors, message.toString());
    }
}

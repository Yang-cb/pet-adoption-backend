package com.ycb.aspect;

import com.ycb.annotation.AutoFill;
import com.ycb.common.constant.AutoFillConstant;
import com.ycb.common.constant.StatusConstant;
import com.ycb.common.enumerate.OperationType;
import com.ycb.exception.ReflectException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;

/**
 * 字段自动填充切面
 */
@Aspect
@Component
public class AutoFillAspect {
    /**
     * 自动填充切点
     * annotation 指定注解 带有 AutoFill 注解的方法
     * execution  指定方法  com.ycb.mapper 任意返回值 任意包名 任意类 任意方法 任意参数
     */
    @Pointcut("@annotation(com.ycb.annotation.AutoFill) && execution(* com.ycb.mapper.*.*(..))")
    public void autoFillPointcut() {
    }

    /**
     * 自动填充前置通知
     */
    @Before("autoFillPointcut()")
    public void autoFill(@NotNull JoinPoint joinPoint) {
        // 获取当前拦截方法的数据库操作类型
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        AutoFill annotation = signature.getMethod().getAnnotation(AutoFill.class);
        OperationType operationType = annotation.value();
        // 获取当前拦截方法的参数
        Object[] args = joinPoint.getArgs();
        if (args == null || args.length == 0) {
            return;
        }
        Object object = args[0];
        // 赋值的数据
        LocalDateTime now = LocalDateTime.now();
        // 填充字段
        try {
            // 修改时间：所有操作均需要填充该字段
            object.getClass().getDeclaredMethod(AutoFillConstant.GMT_MODIFIED, LocalDateTime.class)
                    .invoke(object, now);
            if (operationType == OperationType.INSERT) {
                // 新增操作
                object.getClass().getDeclaredMethod(AutoFillConstant.GMT_CREATE, LocalDateTime.class)
                        .invoke(object, now);
                object.getClass().getDeclaredMethod(AutoFillConstant.IS_DELETE, Integer.class)
                        .invoke(object, StatusConstant.NOT_DELETED);
            } else if (operationType == OperationType.DELETE) {
                // 删除操作
                object.getClass().getDeclaredMethod(AutoFillConstant.IS_DELETE, Integer.class)
                        .invoke(object, StatusConstant.DELETED);
            }
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new ReflectException(e.getMessage());
        }
    }
}

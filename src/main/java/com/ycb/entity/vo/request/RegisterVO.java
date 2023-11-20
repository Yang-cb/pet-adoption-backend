package com.ycb.entity.vo.request;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * 注册请求封装对象
 */
@Data
public class RegisterVO {
    /**
     * 邮箱
     */
    @NotNull(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;
    /**
     * 验证码
     */
    @NotNull(message = "验证码不能为空")
    @Length(min = 6, max = 6, message = "验证码有误")
    private String code;
    /**
     * 用户名
     */
    @NotNull(message = "用户名不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9_]{2,10}$", message = "用户名格式不正确")
    private String username;
    /**
     * 密码
     */
    @NotNull(message = "密码不能为空")
    @Length(min = 6, max = 16, message = "密码长度为6-16位")
    @Pattern(regexp = "^\\S{6,16}$", message = "密码不能包含空格")
    private String password;
}

package com.ycb.pojo.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 重置密码封装对象
 */
@Data
public class ResetPwDTO {
    /**
     * 邮箱
     */
    @NotNull(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;
    /**
     * 验证码
     */
    private String code;
    /**
     * 密码
     */
    @NotNull
    @Pattern(regexp = "^\\S{6,16}$", message = "密码格式不正确")
    private String password;
    /**
     * 重置时间
     */
    private LocalDateTime gmtModified;
}

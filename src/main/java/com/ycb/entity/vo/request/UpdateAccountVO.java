package com.ycb.entity.vo.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.sql.Date;

/**
 * 修改用户信息
 */
@Data
public class UpdateAccountVO {
    /**
     * 用户id
     */
    @NotNull(message = "用户id不能为空")
    private Integer id;
    /**
     * 地址
     */
    private String location;
    /**
     * 昵称
     */
    @NotNull(message = "昵称不能为空")
    @Pattern(regexp = "^[\\u4e00-\\u9fa5_a-zA-Z0-9]{2,8}$",
            message = "昵称只能是由2-8位中文、英文、数字或下划线组成")
    private String nikeName;
    /**
     * 出生日期
     */
    private Date birthday;
    /**
     * 性别
     */
    @NotNull
    @Min(value = 0, message = "性别格式有误")
    @Max(value = 2, message = "性别格式有误")
    private Integer sex;
    /**
     * 签名
     */
    private String signature;
}

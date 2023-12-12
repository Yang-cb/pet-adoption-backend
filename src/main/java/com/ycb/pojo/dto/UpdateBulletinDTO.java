package com.ycb.pojo.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

/**
 * 更新布告请求封装对象
 */
@Data
public class UpdateBulletinDTO {
    /**
     * 宠物id
     */
    @NotNull(message = "宠物id不能为空")
    private Integer petId;
    /**
     * 布告id
     */
    @NotNull(message = "布告id不能为空")
    private Integer bulletinId;
    /**
     * 用户id
     */
    @NotNull(message = "用户id不能为空")
    private Integer accountId;
    /**
     * 宠物姓名
     */
    private String petName;
    /**
     * 宠物类型
     */
    @NotNull(message = "宠物类型不能为空")
    @Pattern(regexp = "^(cat|dog|other)$", message = "宠物类型有误")
    private String petType;
    /**
     * 宠物性别 1公0母
     */
    @NotNull(message = "请指定宠物性别")
    @Min(value = 0, message = "宠物性别格式有误")
    @Max(value = 1, message = "宠物性别格式有误")
    private Integer petSex;
    /**
     * 是否免费 1是0否
     */
    @NotNull(message = "请指定是否免费")
    @Min(value = 0, message = "是否免费格式有误")
    @Max(value = 1, message = "是否免费格式有误")
    private Integer isFree;
    /**
     * 是否绝育
     */
    @NotNull(message = "请指定是否绝育")
    @Min(value = 0, message = "是否绝育格式有误")
    @Max(value = 1, message = "是否绝育格式有误")
    private Integer isSterilization;
    /**
     * 健康状态
     */
    @NotNull(message = "健康状态不能为空")
    private String petHealthStatus;
    /**
     * 宠物照片
     */
    private MultipartFile file;
    /**
     * 照片类型
     */
    @NotNull(message = "照片类型不能为空")
    private String picType;
    /**
     * 领养地址
     */
    @NotNull(message = "领养地址不能为空")
    private String location;
    /**
     * 联系人
     */
    @NotNull(message = "联系人不能为空")
    @Length(min = 1, max = 5, message = "联系人长度有误")
    private String contactsName;
    /**
     * 手机号
     */
    @NotNull(message = "手机号不能为空")
    @Length(min = 11, max = 11, message = "手机号长度有误")
    @Pattern(regexp = "^1[3456789]\\d{9}$", message = "手机号格式不正确")
    private String contactsPhone;
    /**
     * 微信：由6至20个字符组成，可以包含字母、数字、下划线和减号，但必须以字母开头。微信号不区分大小写。
     */
    @Pattern(regexp = "(^$)|^[a-zA-Z][a-zA-Z0-9_-]{5,19}$", message = "微信号格式不正确")
    private String contactsWechat;
    /**
     * 邮箱
     */
    @Email(message = "邮箱格式不正确")
    private String contactsEmail;
    /**
     * 标题
     */
    @NotNull(message = "标题不能为空")
    @Length(min = 1, max = 20, message = "标题长度标题长度不能大于20")
    private String title;
    /**
     * 内容
     */
    @NotNull(message = "详细内容不能为空")
    @Length(min = 1, max = 300, message = "详细内容长度不能大于300字")
    private String text;
    /**
     * 布告状态
     */
    @NotNull(message = "状态不能为空")
    @Min(value = 0, message = "状态格式有误")
    @Max(value = 2, message = "状态格式有误")
    private Integer bulletinStatus;
    /**
     * 请求类型：想领养，求抱走
     */
    @NotNull(message = "请求类型不能为空")
    @Pattern(regexp = "^(adopt|away)$", message = "请求类型有误")
    private String type;
}
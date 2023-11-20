package com.ycb.entity.vo.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * 更新用户头像请求封装对象
 */
@Data
public class UpdateAccPicVO {
    /**
     * 用户id
     */
    @NotNull(message = "用户id不能为空")
    private Integer id;
    /**
     * 头像
     */
    @NotNull(message = "头像不能为空")
    private MultipartFile file;
    /**
     * 请求类型
     */
    @NotNull(message = "请求类型不能为空")
    @Pattern(regexp = "^acc$", message = "请求类型有误")
    private String type;
}

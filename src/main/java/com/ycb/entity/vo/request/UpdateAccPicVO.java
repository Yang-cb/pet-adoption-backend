package com.ycb.entity.vo.request;

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
    private Integer id;
    /**
     * 头像
     */
    private MultipartFile file;
    /**
     * 请求类型
     */
    private String type;
}

package com.ycb.pojo.entity;

import lombok.Data;

/**
 * 图片
 */
@Data
public class Picture {
    /**
     * 图片自增id
     */
    private Integer picId;
    /**
     * 图片名
     */
    private String picName;
    /**
     * 图片类型
     */
    private String picType;
}

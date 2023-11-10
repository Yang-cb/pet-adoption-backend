package com.ycb.entity.dto;

import lombok.Data;

@Data
public class Picture {
    private Integer id;
    /**
     * 图片名
     */
    private String name;
    /**
     * 数据
     */
    private byte[] picData;
}

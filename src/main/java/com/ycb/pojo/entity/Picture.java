package com.ycb.pojo.entity;

import lombok.Data;

import java.time.LocalDateTime;

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
    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;
    /**
     * 修改时间
     */
    private LocalDateTime gmtModified;
    /**
     * 是否删除 1是0否
     */
    private Integer isDelete;
}

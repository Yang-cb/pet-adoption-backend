package com.ycb.mapper;

import com.ycb.entity.dto.Picture;
import org.apache.ibatis.annotations.Mapper;

/**
 * 文件操作mapper
 */
@Mapper
public interface FileMapper {
    void upload(Picture picture);
}

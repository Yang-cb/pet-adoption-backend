package com.ycb.mapper;

import com.ycb.annotation.AutoFill;
import com.ycb.common.enumerate.OperationType;
import com.ycb.pojo.entity.Picture;
import org.apache.ibatis.annotations.Mapper;

/**
 * 文件操作mapper
 */
@Mapper
public interface FileMapper {
    @AutoFill(OperationType.INSERT)
    void upload(Picture picture);
}

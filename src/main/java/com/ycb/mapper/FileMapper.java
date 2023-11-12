package com.ycb.mapper;

import com.ycb.entity.dto.Picture;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface FileMapper {
    int upload(Picture picture);
}

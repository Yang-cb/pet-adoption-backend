package com.ycb.mapper;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface FileMapper {
    int upload(String fileName);
}

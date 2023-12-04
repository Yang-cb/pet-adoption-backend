package com.ycb.service;

import com.ycb.pojo.entity.Picture;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件操作的服务接口
 */
public interface FileService {
    Picture upload(MultipartFile file, String type);
}

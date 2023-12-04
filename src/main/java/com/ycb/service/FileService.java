package com.ycb.service;

import com.ycb.pojo.entity.Picture;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件操作的服务接口
 */
public interface FileService {
    /**
     * 上传文件
     * @param file 文件
     * @param type 文件类型
     * @return 文件信息
     */
    Picture upload(MultipartFile file, String type);
}

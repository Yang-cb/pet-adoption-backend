package com.ycb.service.impl;

import com.ycb.entity.dto.Picture;
import com.ycb.mapper.FileMapper;
import com.ycb.service.FileService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Resource
    private FileMapper fileMapper;
    // 图片保存位置
    @Value("${file.img.path}")
    String path;

    @Override
    public Picture upload(MultipartFile file, String type) {
        // 判断文件是否为空
        if (file.isEmpty()) {
            return null;
        }
        // 获取传过来的文件名字
        String OriginalFilename = file.getOriginalFilename();
        // 生成新的文件名字
        String fileName = null;
        if (OriginalFilename != null) {
            fileName = UUID.randomUUID() + "." + OriginalFilename.substring(OriginalFilename.lastIndexOf(".") + 1);
        }
        File dest = new File(path + type + "\\" + fileName);
        // 判断文件是否存在
        if (!dest.getParentFile().exists()) {
            // 不存在就创建一个
            if (!dest.getParentFile().mkdirs()) {
                return null;
            }
        }
        Picture picture = new Picture();
        try {
            // 上传
            file.transferTo(dest);
            picture.setPicName(fileName);
            fileMapper.upload(picture);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return picture;
    }
}

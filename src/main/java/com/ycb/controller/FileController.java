package com.ycb.controller;

import com.ycb.entity.RestBean;
import com.ycb.mapper.FileMapper;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@RestController
@RequestMapping("/api/file")
public class FileController {
    @Resource
    private FileMapper fileMapper;

    // 后台保存位置
    String path = "O:\\workspace\\pet-adoption\\pet-adoption-frontend\\src\\assets\\images\\";

    @PostMapping("/upload")
    public RestBean<String> uploadFile(@RequestParam(value = "file", required = false) MultipartFile file) {
        // 判断文件是否为空
        if (file.isEmpty()) {
            return RestBean.failure(401, "文件为空");
        }
        // 获取传过来的文件名字
        String OriginalFilename = file.getOriginalFilename();
        // 生成新的文件名字
        String fileName = null;
        if (OriginalFilename != null) {
            fileName = UUID.randomUUID() + "." + OriginalFilename.substring(OriginalFilename.lastIndexOf(".") + 1);
        }
        File dest = new File(path + fileName);
        // 判断文件是否存在
        if (!dest.getParentFile().exists()) {
            // 不存在就创建一个
            boolean mkdirs = dest.getParentFile().mkdirs();
        }
        try {
            // 后台上传
            file.transferTo(dest);
            fileMapper.upload(fileName);
        } catch (Exception e) {
            return RestBean.failure(401, e.getMessage());
        }
        return RestBean.success(fileName);
    }
}
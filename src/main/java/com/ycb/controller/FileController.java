package com.ycb.controller;

import com.ycb.common.result.RestBean;
import com.ycb.pojo.entity.Picture;
import com.ycb.exception.FileException;
import com.ycb.service.FileService;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传控制器
 */
@RestController
@RequestMapping("/api/file")
public class FileController {
    @Resource
    private FileService fileService;

    /**
     * 上传文件
     * @param file 文件
     * @param type 文件类型
     * @return 上传结果
     */
    @PostMapping("/upload")
    public RestBean<Picture> uploadFile(@NotNull @RequestParam MultipartFile file,
                                        @NotNull @RequestParam String type) {
        Picture picture = fileService.upload(file, type);
        if (picture == null) {
            throw new FileException();
        }
        return RestBean.success(picture);
    }
}
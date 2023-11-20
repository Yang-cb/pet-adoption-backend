package com.ycb.controller;

import com.ycb.entity.RestBean;
import com.ycb.entity.dto.Picture;
import com.ycb.service.FileService;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/api/file")
public class FileController {
    @Resource
    private FileService fileService;

    @PostMapping("/upload")
    public String uploadFile(@NotNull @RequestParam MultipartFile file,
                             @NotNull @RequestParam String type) {
        Picture picture = fileService.upload(file, type);
        if (picture == null) {
            return RestBean.failure(401, "上传失败").jsonToString();
        }
        return RestBean.success(picture).jsonToString();
    }
}
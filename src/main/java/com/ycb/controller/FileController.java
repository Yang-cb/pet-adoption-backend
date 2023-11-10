package com.ycb.controller;

import com.ycb.entity.RestBean;
import com.ycb.service.FileService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/file")
public class FileController {
    @Resource
    private FileService fileService;

    @PostMapping("/upload")
    public RestBean<String> upload(@RequestParam MultipartFile file) {
        Integer res;
        try {
            res = fileService.upload(file);
        } catch (IOException e) {
            return RestBean.failure(401, e.getMessage());
        }
        return RestBean.success(String.valueOf(res));
    }
}

package com.ycb.service;

import com.ycb.entity.dto.Picture;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    Picture upload(MultipartFile file, String type);
}

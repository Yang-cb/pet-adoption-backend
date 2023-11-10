package com.ycb.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    Integer upload(MultipartFile file) throws IOException;
}

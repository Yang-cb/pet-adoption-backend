package com.ycb.service.impl;

import com.ycb.entity.dto.Picture;
import com.ycb.mapper.FileMapper;
import com.ycb.service.FileService;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Resource
    private FileMapper fileMapper;

    /**
     * 文件上传到数据库
     *
     * @param file
     * @return
     * @throws IOException
     */
    @Override
    public Integer upload(MultipartFile file) throws IOException {
        // 获取文件原始名
        String originalFilename = file.getOriginalFilename();
        // 获取文件后缀名
        String endName = "png";
        if (originalFilename != null) {
            endName = originalFilename.substring(originalFilename.lastIndexOf("."));
        }
        // 拼接文件名
        String filename = UUID.randomUUID() + endName;
        Integer pictureId;
        // 创建图片对象
        byte[] fileBytes = file.getBytes();
        Picture picture = new Picture();
        picture.setName(filename);
        picture.setPicData(fileBytes);
        // 上传数据库
        fileMapper.upload(picture);
        pictureId = picture.getId();
        //返回图片id
        return pictureId;
    }
/*
    public void download(HttpServletResponse response) {
        byte[] file = fileMapper.download(1);
        InputStream is = null;
        ServletOutputStream os = null;
        try {
            is = blob.getBinaryStream();
            response.setContentType("text/html");
            os = response.getOutputStream();
            int num;

            byte[] buff = new byte[1024];
            while ((num = is.read(buff)) != -1) {
                os.write(buff, 0, num);
            }

            try {
                is.close();
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException |
                 SQLException e) {
            throw new RuntimeException(e);
        }
    }
    */

}

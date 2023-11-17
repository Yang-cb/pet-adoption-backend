package com.ycb.entity.vo.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UpdateAccPicVO {
    private Integer id;
    private MultipartFile file;
    private String type;
}

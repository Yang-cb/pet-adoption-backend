package com.ycb.entity.vo.response;

import lombok.Data;


@Data
public class AccountVO {
    private Integer id;
    private String username;
    private String email;
    /**
     * 头像
     */
    private byte[] picData;
}

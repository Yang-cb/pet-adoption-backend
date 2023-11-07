package com.ycb.entity.vo.request;

import lombok.Data;

/**
 * 重置密码封装对象
 */
@Data
public class ResetPwVO {
    private String email;
    private String code;
    private String password;
}

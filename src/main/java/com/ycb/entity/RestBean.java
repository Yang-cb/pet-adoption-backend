package com.ycb.entity;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONWriter;

/**
 * 通用返回对象
 * @param code 状态码 200:成功
 * @param data 返回数据
 * @param message 返回信息
 * @param <T> 返回数据类型
 */
public record RestBean<T>(Integer code, T data, String message) {
    public static <T> RestBean<T> success(T data) {
        return new RestBean<>(200, data, "请求成功");
    }

    public static <T> RestBean<T> failure(Integer code, String message) {
        return new RestBean<>(code, null, message);
    }

    public String jsonToString() {
        return JSON.toJSONString(this, JSONWriter.Feature.WriteNulls);
    }
}

package com.ycb.entity;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONWriter;

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

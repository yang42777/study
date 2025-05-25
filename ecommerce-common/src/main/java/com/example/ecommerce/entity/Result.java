package com.example.ecommerce.entity;

import lombok.Data;

@Data
public class Result<T> {
    private String code;     // 状态码，如 200 表示成功
    private String message;   // 提示信息
    private T data;           // 返回的数据

    // 成功返回
    public static <T> Result<T> success(T data) {
        Result<T> r = new Result<>();
        r.setCode("200");
        r.setMessage("成功");
        r.setData(data);
        return r;
    }

    // 失败返回
    public static <T> Result<T> error(String message) {
        Result<T> r = new Result<>();
        r.setCode("500");
        r.setMessage(message);
        r.setData(null);
        return r;
    }

    // 自定义返回
    public static <T> Result<T> custom(String code, String message, T data) {
        Result<T> r = new Result<>();
        r.setCode(code);
        r.setMessage(message);
        r.setData(data);
        return r;
    }
}
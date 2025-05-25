package com.example.ecommerce.common.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtil {
    // 哈希算法（Hash），自带随机盐，安全性高，防暴力破解
    // Java里常用Spring Security自带的BCryptPasswordEncoder
    // 注册时，把用户明文密码用encrypt()加密，存进数据库。
    // 登录时，用matches()对比用户输入的明文密码和数据库里的密文。
    // 输出类似：$2a$10$DowJ8Whj8mT9TyE4s3mFSeXyq24UluE9S0I7MTWOVoXK7LhE9qF3a，$2a$10$ 是BCrypt版本和强度，后面紧跟的22个字符就是盐（Base64编码），之后是密码的哈希结果
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static String encrypt(String rawPassword) {
        return encoder.encode(rawPassword);
    }

    public static boolean matches(String rawPassword, String encodedPassword) {
        return encoder.matches(rawPassword, encodedPassword);
    }
}

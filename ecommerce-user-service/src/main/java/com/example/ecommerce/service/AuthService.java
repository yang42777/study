package com.example.ecommerce.service;

import com.example.ecommerce.exception.BizCodeException;

import java.util.Map;

public interface AuthService {
    Map<String, Object> login(String username, String password) throws BizCodeException;

    Map<String, Object> register(String username, String password) throws BizCodeException;

    Map<String, Object> logout(String token);
}

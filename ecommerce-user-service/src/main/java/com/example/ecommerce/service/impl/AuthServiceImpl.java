package com.example.ecommerce.service.impl;

import com.example.ecommerce.constants.ErrorCodes;
import com.example.ecommerce.dao.UserMapper;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.exception.BizCodeException;
import com.example.ecommerce.service.AuthService;
import com.example.ecommerce.utils.JwtUtil;
import com.example.ecommerce.utils.PasswordUtil;
import com.example.ecommerce.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Map<String, Object> login(String username, String password) throws BizCodeException {
        User user = userMapper.findByUsername(username);
        Map<String, Object> result = new HashMap<>();
        if (user == null || !PasswordUtil.matches(password, user.getPassword())) {
            BizCodeException.publish(ErrorCodes.INVALID_PASSWORD);
        }
        String token = JwtUtil.generateToken(user.getId(), user.getUsername());

        RedisUtil.set("SESSION:" + token, user.getId(), 3600); // 1小时过期

        result.put("success", true);
        result.put("token", token);
        result.put("username", user.getUsername());

        return result;
    }

    @Override
    public Map<String, Object> register(String username, String password) throws BizCodeException {
        Map<String, Object> result = new HashMap<>();

        User existing = userMapper.findByUsername(username);
        if (existing != null) {
            BizCodeException.publish(ErrorCodes.USER_EXISTS);
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(PasswordUtil.encrypt(password)); // 实际中应加密

        userMapper.insert(user);

        result.put("success", true);
        result.put("message", "Registration successful");
        return result;
    }

    @Override
    public Map<String, Object> logout(String token) {
        RedisUtil.delete("SESSION:" + token);

        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("message", "Logout successful");

        return result;
    }
}

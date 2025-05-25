package com.example.ecommerce.user.service.impl;

import com.example.ecommerce.user.dao.UserMapper;
import com.example.ecommerce.user.entity.User;
import com.example.ecommerce.user.service.AuthService;
import com.example.ecommerce.common.utils.JwtUtil;
import com.example.ecommerce.common.utils.PasswordUtil;
import com.example.ecommerce.common.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public Map<String, Object> login(String username, String password) {
        User user = userMapper.findByUsername(username);
        Map<String, Object> result = new HashMap<>();
        if (user == null || !PasswordUtil.matches(password, user.getPassword())) {
            result.put("success", false);
            result.put("message", "Invalid username or password");
            return result;
        }
        String token = JwtUtil.generateToken(user.getId(), user.getUsername());

        redisUtil.set("SESSION:" + token, user.getId(), 3600); // 1小时过期

        result.put("success", true);
        result.put("token", token);
        result.put("username", user.getUsername());

        return result;
    }

    @Override
    public Map<String, Object> register(String username, String password) {
        Map<String, Object> result = new HashMap<>();

        User existing = userMapper.findByUsername(username);
        if (existing != null) {
            result.put("success", false);
            result.put("message", "Username already exists");
            return result;
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
        redisUtil.delete("SESSION:" + token);

        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("message", "Logout successful");

        return result;
    }
}

package com.example.ecommerce.user.controller;

import com.example.ecommerce.user.dto.LoginRequest;
import com.example.ecommerce.user.service.AuthService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

@RestController
@RequestMapping("/api/loginRequest")
public class LoginRequestController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody LoginRequest request) {
        return authService.login(request.getUsername(), request.getPassword());
    }

    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody LoginRequest request) {
        return authService.register(request.getUsername(), request.getPassword());
    }

    @PostMapping("/logout")
    public Map<String, Object> logout(@RequestHeader("Authorization") String token) {
        return authService.logout(token);
    }
}
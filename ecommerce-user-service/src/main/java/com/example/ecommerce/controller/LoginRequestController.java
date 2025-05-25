package com.example.ecommerce.controller;

import com.example.ecommerce.dto.LoginRequest;
import com.example.ecommerce.entity.Result;
import com.example.ecommerce.exception.BizCodeException;
import com.example.ecommerce.service.AuthService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class LoginRequestController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public Result<Map<String, Object>> login(@RequestBody LoginRequest request) throws BizCodeException {
        return Result.success(authService.login(request.getUsername(), request.getPassword()));
    }

    @PostMapping("/register")
    public Result<Map<String, Object>> register(@RequestBody LoginRequest request) throws BizCodeException {
        return Result.success(authService.register(request.getUsername(), request.getPassword()));
    }

    @PostMapping("/logout")
    public Result<Map<String, Object>> logout(@RequestHeader("Authorization") String token) {
        return Result.success((authService.logout(token)));
    }

    @PostMapping("/notify-admin")
    public Result<String> notifyAdmin(@RequestBody Map request) {
        //String productId = request.getProductId();
        // 调用邮件服务等，通知管理员
        //emailService.sendPaymentNotification(productId);
        return Result.success("已通知管理员");
    }
}
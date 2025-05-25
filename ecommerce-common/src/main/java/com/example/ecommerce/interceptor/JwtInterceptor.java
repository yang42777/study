package com.example.ecommerce.common.interceptor;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import com.example.ecommerce.common.utils.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 拦截除登录注册外所有接口
@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty()) {
            response.setStatus(401);
            response.getWriter().write("Missing token");
            return false;
        }

        try {
            Claims claims = JwtUtil.parseToken(token);
            request.setAttribute("userId", claims.getSubject());
            request.setAttribute("username", claims.get("username"));
            return true;
        } catch (ExpiredJwtException e) {
            response.setStatus(401);
            response.getWriter().write("Token expired");
            return false;
        } catch (Exception e) {
            response.setStatus(401);
            response.getWriter().write("Invalid token");
            return false;
        }
    }
}

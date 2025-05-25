package com.example.ecommerce.config;

import com.example.ecommerce.interceptor.JwtInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private JwtInterceptor jwtInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor).addPathPatterns("/user/**").excludePathPatterns("/user/login", "/user/register", "/user/notify-admin");

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 映射 /resource/static/** 到 classpath:/static/resource/static/
        registry.addResourceHandler("/resource/static/**")
                .addResourceLocations("classpath:/static/resource/static/");
    }
}

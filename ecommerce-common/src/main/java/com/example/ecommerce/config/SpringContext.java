package com.example.ecommerce.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringContext implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        SpringContext.context = applicationContext;
    }

    // 通过类型获取Bean
    public static <T> T getBean(Class<T> clazz) {
        return context.getBean(clazz);
    }

    // 通过名称获取Bean
    public static Object getBean(String beanName) {
        return context.getBean(beanName);
    }
}
package com.example.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.ecommerce")
public class OderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OderApplication.class, args);
    }
}

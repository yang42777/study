package com.example.ecommerce;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

import java.util.Collections;

public class CodeGenerator {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/ecommerce?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai", "ecommerce_user", "ecommerce_pwd")
                .globalConfig(builder -> {
                    builder.author("zfy") // 作者名
                            .enableSwagger() // 开启 Swagger 注解（可选）
                            .outputDir(System.getProperty("user.dir") + "/src/main/java"); // 代码输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.example.ecommerce;") // 包名前缀
                            //.moduleName("ecommerce-product-service") // 模块名（可选）
                            .pathInfo(Collections.singletonMap(OutputFile.xml, System.getProperty("user.dir") + "/src/main/resources/com/example/ecommerce/mapper"));
                })
                .strategyConfig(builder -> {
                    builder.addInclude("main_order", "order_item") // 表名，多个用逗号分隔
                            .addTablePrefix("t_", "sys_") // 去除表前缀（如 t_product → Product）
                            .entityBuilder().enableLombok()
                            .mapperBuilder().enableBaseResultMap().enableBaseColumnList();
                })
                .templateEngine(new VelocityTemplateEngine()) // 使用 Velocity 引擎
                .execute();
    }
}
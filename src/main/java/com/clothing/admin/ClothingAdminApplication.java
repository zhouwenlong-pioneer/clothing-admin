package com.clothing.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.clothing.admin.mapper")
public class ClothingAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClothingAdminApplication.class, args);
    }
}

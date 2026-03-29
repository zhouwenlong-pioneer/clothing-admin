package com.clothing.admin.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("服装商品后台管理系统 API")
                .description("服装商品后台管理系统接口文档")
                .version("1.0.0")
                .contact(new Contact()
                    .name("Clothing Admin")
                    .email("admin@clothing.com")));
    }
}

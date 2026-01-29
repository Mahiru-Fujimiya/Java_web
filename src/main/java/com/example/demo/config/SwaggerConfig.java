package com.example.demo.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI techShopAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Tech Shop API Documentation")
                        .description("Tài liệu API cho hệ thống quản lý cửa hàng công nghệ")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Tech Shop Support")
                                .email("support@techshop.com")
                        )
                )
                .externalDocs(new ExternalDocumentation()
                        .description("Github Repository")
                        .url("https://github.com/your-repo-here"));
    }
}

package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // Cho phép truy cập file tĩnh (ảnh upload)
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        // ánh xạ: /uploads/** → thư mục: src/main/resources/static/uploads
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("classpath:/static/uploads/");
    }

    // Cấu hình CORS (có thể dùng song song với CorsConfig)
    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addCorsMappings("/**")
                .allowedOrigins(
                        "http://localhost:3000",
                        "http://localhost:5173",
                        "http://127.0.0.1:5173"
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }

    // Cấu hình redirect từ "/" → "/index"
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
    }
}

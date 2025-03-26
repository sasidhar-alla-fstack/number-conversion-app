package com.numberconversion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Apply CORS settings to all endpoints
                    .allowedOrigins(new String[]{"http://localhost:3000", "http://localhost:3001", "http://localhost:9090"}) // Allow frontend origin
                    .allowedMethods("GET") // Allowed HTTP methods
                    .allowedHeaders("*") // Allow all headers
                    .allowCredentials(true); // Allow credentials (e.g., cookies, authorization headers)
            }
        };
    }
}
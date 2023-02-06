package com.timbar.wmi.prapro2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class Prapro2Application {

    private static final long MAX_AGE_SECS = 3600;
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS");
//                        .allowedHeaders("*")
//                        .allowCredentials(true)
//                        .maxAge(MAX_AGE_SECS);
            }
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(Prapro2Application.class, args);
    }

}

package com.designpattern.webmotosystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // couvre toutes les routes (documents, api, etc.)
                        .allowedOrigins(
                                "http://localhost:8080",   // si tu utilises un serveur web classique
                                "http://localhost:5500",   // si tu utilises Live Server ou Python http.server
                                "null"                     // si tu ouvres ton frontend en file://
                        )
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(false);
            }
        };
    }
}

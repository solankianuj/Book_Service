package com.example.bookService.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BookServiceConfig {

    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
}

package org.example.densitydata.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfig {
    @Bean //dependency injection 용 restTemplate bean 처리
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

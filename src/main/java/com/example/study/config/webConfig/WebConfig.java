package com.example.study.config.webConfig;

import com.example.study.config.interceptor.ProcessingInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer configurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                // ProcessingInterceptor를 InterceptorRegistry에 등록
                registry.addInterceptor(new ProcessingInterceptor());
            }
        };
    }
}

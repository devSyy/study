package com.example.study;

import com.example.study.config.interceptor.ProcessingInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableJpaAuditing
public class StudyApplication {
	public static void main(String[] args) {
		SpringApplication.run(StudyApplication.class, args);
	}
}

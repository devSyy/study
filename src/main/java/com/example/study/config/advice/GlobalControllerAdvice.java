package com.example.study.config.advice;

import com.example.study.config.response.CommonResponse;
import com.example.study.config.response.DataResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalControllerAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        HttpServletRequest servletRequest = ((ServletServerHttpRequest) request).getServletRequest();
        if (servletRequest.getAttribute("startTime") != null) {
            long startTime = (long) servletRequest.getAttribute("startTime");
            long timeElapsed = System.currentTimeMillis() - startTime;
            response.getHeaders().add("elapsedTime", timeElapsed + " ms");

            if (body instanceof DataResponse<?>) {
                ((DataResponse<?>) body).setElapsedTime(timeElapsed + " ms");
            }
        }
        return body;
    }
}
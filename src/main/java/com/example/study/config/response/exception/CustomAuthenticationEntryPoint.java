package com.example.study.config.response.exception;

import com.example.study.config.response.ResponseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Slf4j
@RequiredArgsConstructor
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.warn(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"))+" : Exception transmitted to AuthenticationEntryPoint");

        String exception = (String)request.getAttribute("exception");
        log.info("exception > {}", exception);
        if (exception.equals(CustomExceptionStatus.NOT_AUTHENTICATED_ACCOUNT.getMessage())) {
            log.info(CustomExceptionStatus.NOT_AUTHENTICATED_ACCOUNT.getMessage());
            response.setStatus(400);
            response.setCharacterEncoding("utf-8");
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getWriter().write(objectMapper.writeValueAsString(
                    new ResponseService().getExceptionResponse(CustomExceptionStatus.NOT_AUTHENTICATED_ACCOUNT)
            ));
        }
        else if (exception.equals(CustomExceptionStatus.INVALID_JWT.getMessage())) {
            log.info(CustomExceptionStatus.INVALID_JWT.getMessage());
//            response.sendRedirect("/errors/invalid-jwt");
        }
        else if (exception.equals(CustomExceptionStatus.EMPTY_JWT.getMessage())) {
            log.info(CustomExceptionStatus.EMPTY_JWT.getMessage());
//            response.sendRedirect("/errors/empty-jwt");
        }
    }
}

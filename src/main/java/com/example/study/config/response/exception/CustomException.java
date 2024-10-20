package com.example.study.config.response.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor //모든 필드 값을 파라미터로 받는 생성자를 생성
public class CustomException extends RuntimeException {
    CustomExceptionStatus customExceptionStatus;
}

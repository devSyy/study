package com.example.study.config.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommonResponse {

    protected Boolean isSuccess;

    protected int code;

    protected String message;

    protected String elapsedTime;

}
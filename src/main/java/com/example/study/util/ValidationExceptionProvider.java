package com.example.study.util;

import com.example.study.config.response.exception.CustomException;
import com.example.study.config.response.exception.CustomExceptionStatus;
import org.springframework.validation.Errors;

public class ValidationExceptionProvider {

    public static void throwValidError(Errors errors) {
        String errorCode = errors.getFieldError().getCode();
        String errorTarget = errors.getFieldError().getField();
        System.out.print("errorCode : " + errorCode);
        System.out.print("errorTarget : " + errorTarget);
        throw new CustomException(ValidationExceptionProvider.getExceptionStatus(errorCode, errorTarget));
    }

    public static CustomExceptionStatus getExceptionStatus(String code, String target) {
        if (code.equals("NotBlank")){
            if (target.equals("email")) return CustomExceptionStatus.POST_USERS_EMPTY_EMAIL;
            else if (target.equals("password")) return CustomExceptionStatus.POST_USERS_EMPTY_PASSWORD;
        }
        else if (code.equals("Pattern") || code.equals("Length")){
            if (target.equals("nickname")) return CustomExceptionStatus.POST_USERS_INVALID_NICKNAME;
            else if (target.equals("password")) return CustomExceptionStatus.POST_USERS_INVALID_PASSWORD;
        }
        else if (code.equals("Email")) {
            return CustomExceptionStatus.POST_USERS_INVALID_EMAIL;
        }
        return CustomExceptionStatus.RESPONSE_ERROR;
    }
}

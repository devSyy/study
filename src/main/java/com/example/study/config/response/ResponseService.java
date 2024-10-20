package com.example.study.config.response;

import com.example.study.config.response.exception.CustomExceptionStatus;
import org.springframework.stereotype.Service;

@Service
public class ResponseService {

    /**
     * <T>: 이 메서드가 제네릭 타입을 사용할 것임을 선언합니다.
     * DataResponse<T>: 반환 타입이 DataResponse이며, 이 DataResponse 객체는 제네릭 타입 T를 포함합니다.
     * T data: 메서드에 전달된 매개변수로, 제네릭 타입 T의 데이터를 의미합니다.
     * @param data
     * @return
     * @param <T>
     */
    public CommonResponse getSuccessResponse(){
        CommonResponse response = new CommonResponse();
        response.setIsSuccess(true);
        response.setCode(1000);
        response.setMessage("요청에 성공하였습니다.");
        return response;
    }

    public <T> DataResponse<T> getDataResponse(T data) {
        DataResponse<T> response = new DataResponse<>();
        response.setResult(data);
        response.setIsSuccess(true);
        response.setCode(1000);
        response.setMessage("요청에 성공하였습니다.");
        return response;
    }

    public CommonResponse getExceptionResponse(CustomExceptionStatus status) {
        CommonResponse response = new CommonResponse();
        response.setIsSuccess(status.isSuccess());
        response.setCode(status.getCode());
        response.setMessage(status.getMessage());
        return response;
    }

}

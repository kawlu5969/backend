package com.kawlul.demo.common.dto;

import com.kawlul.demo.common.exception_handler.custom.SecurityErrorType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ApiResponse<T> {
    private String code;
    private String message;
    private T data;

    @Builder
    private ApiResponse(String code, String message, T response){
        this.code = code;
        this.message = message;
        this.data = response;
    }

    public static <T> ApiResponse<T> success(T data){
        return successWithMessage(data,"success");
    }
    public static <T> ApiResponse<T> successWithMessage(T data,String message){
        return ApiResponse.<T>builder()
                .code("200")
                .message(message)
                .response(data)
                .build();
    }

    public static ApiResponse<ErrorResponse> of(ErrorResponse errorResponse){
        return ApiResponse.<ErrorResponse>builder()
                .code(Integer.toString(errorResponse.getCode()))
                .message(errorResponse.getMessage())
                .response(errorResponse)
                .build();
    }

}

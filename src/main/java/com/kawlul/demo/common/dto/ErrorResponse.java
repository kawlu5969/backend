package com.kawlul.demo.common.dto;

import com.kawlul.demo.common.exception_handler.custom.SecurityErrorType;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

/**
 * API요청에 대해 문제가 발생했을 때 반환하는 클래스
 *
 */


@Getter
public class ErrorResponse {
    private int code;
    private String message;
    private String field;

    @Builder
    private ErrorResponse(int code, String message,String field) {
        this.code = code;
        this.message = message;
        this.field = field;
    }

    public static ErrorResponse of(SecurityErrorType securityErrorType) {
        return ErrorResponse.builder()
                .code(securityErrorType.getCode())
                .message(securityErrorType.getDetails())
                .field(securityErrorType.getField())
                .build();
    }

    public static ErrorResponse of(HttpStatus status, String message) {
        return ErrorResponse.builder()
                .code(status.value())
                .message(message)
                .build();
    }

    public static ErrorResponse of(BindingResult bindingResult) {
        String message = "";

        if (bindingResult.hasErrors()) {
            message = bindingResult.getAllErrors().get(0).getDefaultMessage();
        }

        return ErrorResponse.of(HttpStatus.BAD_REQUEST, message);
    }
}

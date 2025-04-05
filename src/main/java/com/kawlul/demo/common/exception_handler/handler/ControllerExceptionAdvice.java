package com.kawlul.demo.common.exception_handler.handler;

import com.kawlul.demo.common.dto.ErrorResponse;
import com.kawlul.demo.common.exception_handler.custom.SecurityRestApiException;
import com.kawlul.demo.user_info.Exception.UserErrorType;
import com.kawlul.demo.user_info.Exception.UserRestApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.Console;
import java.util.Optional;


@ControllerAdvice
@Slf4j
public class ControllerExceptionAdvice {
    /**
     * 인증, 인가와 관련된 오류를 처리하는 메서드
     * @see com.kawlul.demo.common.exception_handler.custom.SecurityRestApiException
     * @param securityRestApiException
     * @return
     */
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(SecurityRestApiException securityRestApiException){
        int errorCode = securityRestApiException.getCode();
        String message = securityRestApiException.getDetails();
        log.error(message);
        ErrorResponse errorResponse = ErrorResponse.builder()
                .code(errorCode)
                .message(message)
                .field(securityRestApiException.getField())
                .build();

        return ResponseEntity.status(errorCode).body(errorResponse);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(UserRestApiException userRestApiException){
        log.error(userRestApiException.getMessage());

        UserErrorType userErrorType = userRestApiException.getUserErrorType();
        ErrorResponse errorResponse = ErrorResponse.builder()
                .code(userErrorType.getCode())
                .message(userErrorType.getDetails())
                .build();

        return ResponseEntity.status(userErrorType.getCode()).body(errorResponse);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(Exception exception){
        log.error(exception.getMessage());

        ErrorResponse errorResponse = ErrorResponse.builder()
                .code(400)
                .message("Unknown Dispatcher Exception")
                .build();

        return ResponseEntity.status(400).body(errorResponse);
    }

}

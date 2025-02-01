package com.kawlul.demo.common.exception_handler.custom;

import lombok.Getter;


@Getter
public class RestApiException extends RuntimeException {
    private final ErrorType errorType;
    public RestApiException(ErrorType errorType){this.errorType = errorType;}
}

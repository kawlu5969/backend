package com.kawlul.demo.common.exception_handler.custom;

import lombok.Getter;

@Getter
public enum ErrorType {

    SECURITY("security exception : ");
    private final String message;

    ErrorType(String message){
        this.message = message;
    }
}

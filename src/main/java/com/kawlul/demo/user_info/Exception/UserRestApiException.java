package com.kawlul.demo.user_info.Exception;

import lombok.Getter;

@Getter
public class UserRestApiException extends RuntimeException{
    private UserErrorType userErrorType;
    public UserRestApiException(UserErrorType userErrorType){
        this.userErrorType = userErrorType;
    }
}

package com.kawlul.demo.common.exception_handler.custom;

import lombok.Getter;


public class SecurityRestApiException extends RestApiException{
    private SecurityErrorType securityErrorType;

    public SecurityRestApiException(SecurityErrorType securityErrorType){
        super(ErrorType.SECURITY);
        this.securityErrorType = securityErrorType;
    }

    public String getDetails(){
        return super.getErrorType().getMessage()+securityErrorType.getDetails();
    }

    public String getField(){
        return securityErrorType.getField();
    }
    public Integer getCode(){return securityErrorType.getCode();}
}

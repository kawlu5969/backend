package com.kawlul.demo.common.exception_handler.custom;

import lombok.Getter;

@Getter
public enum SecurityErrorType {
    COOKIE_NOT_EXISTS(401,"쿠키가 존재하지 않습니다.","cn"),
    JWT_NOT_EXISTS(401,"JWT가 존재하지 않습니다.","jn"),
    JWT_NOT_VALID(401,"유효한 JWT가 아닙니다.","juv"),
    UNKNOWN(400, "알 수 없는 오류가 발생했습니다.","un"),
    DUPLICATE_EMAIL(400,"Email is duplicated","ed"),
    DUPLICATE_NAME(400,"Name is duplicated","nd"),
    USER_NOT_EXISTS(400,"User doesn't exist","une"),
    PASSWORD_NOT_MATCHES(400,"Password doesn't match","pdm");
    private final int code;
    private final String details;
    private final String field;

    SecurityErrorType(int code,String details,String field){
        this.code = code;
        this.details = details;
        this.field = field;
    }

}

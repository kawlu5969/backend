package com.kawlul.demo.security.error;


import lombok.Getter;

@Getter
public enum SecurityErrorType {
    COOKIE_NOT_EXISTS(400,"쿠키가 존재하지 않습니다."),
    JWT_NOT_EXISTS(400,"JWT가 존재하지 않습니다."),
    JWT_NOT_VALID(400,"유효한 JWT가 아닙니다."),
    UNKNOWN(400, "알 수 없는 오류가 발생했습니다.");

    private final int code;
    private final String message;

    SecurityErrorType(int code, String message){
        this.code = code;
        this.message = message;
    }
}

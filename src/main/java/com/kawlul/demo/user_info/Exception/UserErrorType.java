package com.kawlul.demo.user_info.Exception;

import lombok.Getter;

@Getter
public enum UserErrorType {
    NAME_DUPLICATED(400,"중복되는 이름이 존재합니다."),
    EMAIL_DUPLICATED(400,"중복되는 이메일이 존재합니다.");

    private final int code;
    private final String details;

    UserErrorType(int code,String details){
        this.code = code;
        this.details = details;

    }
}

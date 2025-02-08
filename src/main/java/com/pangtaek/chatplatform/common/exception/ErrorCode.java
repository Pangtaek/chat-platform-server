package com.pangtaek.chatplatform.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode implements CodeInterface {
    SUCCESS(0, "SUCCESS"),
    USER_ALREADY_EXISTS(-1, "USER_ALREADY_EXISTS"),
    USER_SAVED_FAILED(-2, "USER_SAVED_FAILED"),
    NOT_EXIST_USER(-3, "NOT_EXIST_USER"),
    MISS_MATCH_PASSWORD(-4, "MISS_MATCH_PASSWORD"),
            
    // JWT
    TOEKN_IS_INVALID(-200, "token is invalid"),
    ACCESS_TOKEN_IS_NOT_EXPIRED(-201, "token is not expired"),
    TOKEN_IS_EXPIRED(-202, "token is expired");

    private final Integer code;
    private final String message;
}

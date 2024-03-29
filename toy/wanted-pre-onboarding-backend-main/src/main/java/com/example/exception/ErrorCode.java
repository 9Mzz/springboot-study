package com.example.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    EMAIL_DUPLICATED(HttpStatus.CONFLICT, ""),
    EMAIL_NOT_FOUND(HttpStatus.NOT_FOUND, ""),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, ""),
    NOT_AUTHOR(HttpStatus.BAD_REQUEST, "");

    private HttpStatus httpStatus;
    private String message;
}

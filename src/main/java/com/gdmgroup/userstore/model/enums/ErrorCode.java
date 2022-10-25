package com.gdmgroup.userstore.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    VALIDATE_ERROR("VALIDATE_ERROR", HttpStatus.BAD_REQUEST, "Object validation error"),
    BAD_REQUEST("BAD_REQUEST", HttpStatus.BAD_REQUEST, "Bad request error"),
    NOT_FOUND("NOT_FOUND", HttpStatus.NOT_FOUND, "Not found error"),
    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error"),
    CLIENT_DOES_NOT_EXIST("CLIENT_DOES_NOT_EXIST", HttpStatus.NOT_FOUND, "Client does not exist");

    private final String code;
    private final HttpStatus httpStatus;
    private final String message;
}

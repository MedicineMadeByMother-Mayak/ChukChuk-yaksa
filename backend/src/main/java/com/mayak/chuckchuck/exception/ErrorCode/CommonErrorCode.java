package com.mayak.chuckchuck.exception.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommonErrorCode implements ErrorCode{
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "Invalid parameter included"),
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "Resource not exists"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error"),
    FILE_NOT_FOUND(HttpStatus.NOT_FOUND, "File Not Found"),
    RESULT_NOT_FOUND(HttpStatus.NOT_FOUND, "Result Not Found"),
    ;

    private final HttpStatus httpStatus;
    private final String message;
}

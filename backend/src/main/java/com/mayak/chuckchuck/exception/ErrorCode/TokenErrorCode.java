package com.mayak.chuckchuck.exception.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum TokenErrorCode implements ErrorCode{
    INVALID_REFRESH_TOKEN(HttpStatus.CONFLICT, "Invalid Refresh Token"),
    USER_NOT_FOUND(HttpStatus.CONFLICT, "User Not Found")
    ;

    private final HttpStatus httpStatus;
    private final String message;
}

package com.mayak.chuckchuck.dto.response;

public record UserLoginResponse(
        UserDefaultResponse user,
        String token
) {
}
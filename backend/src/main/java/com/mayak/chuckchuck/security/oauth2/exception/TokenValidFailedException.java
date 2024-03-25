package com.mayak.chuckchuck.security.oauth2.exception;

public class TokenValidFailedException extends RuntimeException{
    public TokenValidFailedException() {
        super("Failed to generate Token.");
    }
}

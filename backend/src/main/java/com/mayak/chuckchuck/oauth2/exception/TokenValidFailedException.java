package com.mayak.chuckchuck.oauth2.exception;

public class TokenValidFailedException extends RuntimeException{
    public TokenValidFailedException() {
        super("Failed to generate Token.");
    }
}

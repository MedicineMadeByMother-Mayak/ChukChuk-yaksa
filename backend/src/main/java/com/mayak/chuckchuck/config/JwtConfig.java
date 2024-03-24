package com.mayak.chuckchuck.config;

import com.mayak.chuckchuck.jwt.AuthTokenProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {
    @Value("${jwt.secret}")
    private String key;

    @Bean
    public AuthTokenProvider jwtProvider() {
        return new AuthTokenProvider(key);
    }
}

package com.mayak.chuckchuck.security.jwt;

import com.mayak.chuckchuck.enums.SocialCode;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
public class AuthToken {

    @Getter
    private final String token;
    private final String key;

    private static final String AUTHORITIES_KEY = "role";

    AuthToken(String id, String key, long expiry) {
        this.token = generateToken(id, key, expiry);
        this.key = key;
    }

    AuthToken(String id ,String role, SocialCode socialCode, String key, long expiry) {
        this.token = generateToken(id, role, socialCode, key, expiry);
        this.key = key;
    }

    public String getUserEmail() {
        return Objects.requireNonNull(extractClaims()).get("email", String.class);
    }

    public SocialCode getSocialCode() {
        String socialCodeStr = Objects.requireNonNull(extractClaims())
                .get("socialCode", String.class);
        try {
            return SocialCode.valueOf(socialCodeStr.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            log.error("Invalid or missing socialCode in JWT: " + socialCodeStr, e);
            return null; // 또는 기본값 또는 예외 처리
        }
    }

    public boolean isExpired() {
        Date expiredDate = Objects.requireNonNull(extractClaims()).getExpiration();
        return expiredDate.before(new Date());
    }

    public boolean validate() {
        return extractClaims() != null;
    }

    public Claims extractClaims() {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getKey(key))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (SecurityException e) {
            log.info("Invalid JWT signature.");
        } catch (MalformedJwtException e) {
            log.info("Invalid JWT token.");
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT token.");
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT token.");
        } catch (IllegalArgumentException e) {
            log.info("JWT token compact of handler are invalid.");
        }
        return null;
    }

    public String generateToken(String id, String key, long expiredTimeMs) {
        return Jwts.builder()
                .setSubject(id)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiredTimeMs))
                .signWith(getKey(key), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateToken(String id, String role, SocialCode socialCode, String key, long expiredTimeMs) {
        Claims claims = Jwts.claims();
        claims.put("email", id);
        claims.put("socialCode", socialCode);
        claims.put(AUTHORITIES_KEY, role);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiredTimeMs))
                .signWith(getKey(key), SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims getExpiredClaims() {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getKey(key))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT token");
            return e.getClaims();
        }
        return null;
    }

    private Key getKey(String key) {
        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
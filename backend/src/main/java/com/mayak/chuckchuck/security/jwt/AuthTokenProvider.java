package com.mayak.chuckchuck.security.jwt;

import com.mayak.chuckchuck.enums.SocialCode;
import com.mayak.chuckchuck.security.oauth2.exception.TokenValidFailedException;
import com.mayak.chuckchuck.security.oauth2.user.UserPrincipal;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Collection;

@Slf4j
@RequiredArgsConstructor
public class AuthTokenProvider {
    private final String key;
    private static final String AUTHORITIES_KEY = "role";

    // 리프레시 토큰
    public AuthToken createAuthToken(String id, long expiry) {
        return new AuthToken(id, key, expiry);
    }

    // 액세스 토큰
    public AuthToken createAuthToken(String id, String role, SocialCode socialCode, long expiry) {
        return new AuthToken(id, role, socialCode, key, expiry);
    }

    public AuthToken convertAuthToken(String token) {
        return new AuthToken(token, key);
    }

    public Authentication getAuthentication(AuthToken authToken) {
        if (authToken.validate()) {
            Claims claims = authToken.extractClaims();
            Collection<? extends GrantedAuthority> authorities =
                    Arrays.stream(new String[]{
                                    claims.get(AUTHORITIES_KEY).toString()
                            })
                            .map(SimpleGrantedAuthority::new)
                            .toList();
            UserPrincipal principal = UserPrincipal.of(authToken.getUserEmail(), authToken.getSocialCode() , authorities);
            return new UsernamePasswordAuthenticationToken(principal, authToken, authorities);
        } else {
            throw new TokenValidFailedException();
        }
    }

}

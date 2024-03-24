package com.mayak.chuckchuck.oauth2.user;

import com.mayak.chuckchuck.domain.User;
import com.mayak.chuckchuck.enums.RoleType;
import com.mayak.chuckchuck.enums.SocialCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public record BoardPrincipal(
        String email,
        SocialCode socialCode,
        Collection<? extends GrantedAuthority> authorities,
        Map<String, Object> attributes
) implements UserDetails, OAuth2User, OidcUser {

    public static BoardPrincipal of(String email, SocialCode socialCode, Map<String, Object> attributes) {
        return new BoardPrincipal(
                email,
                socialCode,
                Collections.singletonList(new SimpleGrantedAuthority(RoleType.USER.getCode())),
                attributes
        );
    }

    public static BoardPrincipal of(String email, SocialCode socialCode, Collection<? extends GrantedAuthority> authorities) {
        return new BoardPrincipal(email, socialCode, authorities, Map.of());
    }

    public static BoardPrincipal of(String email, SocialCode socialCode) {
        return of(email, socialCode, Map.of());
    }

    public static BoardPrincipal from(User dto) {
        return of(
                dto.getSocial(),
                dto.getSocialCode()
        );
    }

    public static BoardPrincipal from(User dto, Map<String, Object> attributes) {
        return of(
                dto.getSocial(),
                dto.getSocialCode(),
                attributes
        );
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getName() {
        return email;
    }

    @Override
    public Map<String, Object> getClaims() {
        return null;
    }

    @Override
    public OidcUserInfo getUserInfo() {
        return null;
    }

    @Override
    public OidcIdToken getIdToken() {
        return null;
    }
}


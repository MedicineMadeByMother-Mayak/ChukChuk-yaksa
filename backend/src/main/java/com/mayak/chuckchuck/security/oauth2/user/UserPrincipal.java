package com.mayak.chuckchuck.security.oauth2.user;

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

public record UserPrincipal(
        String email,
        SocialCode socialCode,
        Collection<? extends GrantedAuthority> authorities,
        Map<String, Object> attributes
) implements UserDetails, OAuth2User, OidcUser {

    public static UserPrincipal of(String email, SocialCode socialCode, Map<String, Object> attributes) {
        return new UserPrincipal(
                email,
                socialCode,
                Collections.singletonList(new SimpleGrantedAuthority(RoleType.USER.getCode())),
                attributes
        );
    }

    public static UserPrincipal of(String email, SocialCode socialCode, Collection<? extends GrantedAuthority> authorities) {
        return new UserPrincipal(email, socialCode, authorities, Map.of());
    }

    public static UserPrincipal of(String email, SocialCode socialCode) {
        return of(email, socialCode, Map.of());
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


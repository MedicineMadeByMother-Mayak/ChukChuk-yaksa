package com.mayak.chuckchuck.oauth2.user;

import com.mayak.chuckchuck.enums.SocialCode;
import lombok.ToString;

import java.util.Map;

/**
 * OAuth2 제공자별로 리턴하는 사용자 정보 데이터의 구조와 필드의 이름 등이 다르다. 서비스별로 다른 구조를 통합하기 위한 인터페이스 정의
 */
@ToString
public abstract class OAuth2UserInfo {
    protected Map<String, Object> attributes;

    public OAuth2UserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public abstract String getId();

    public abstract String getName();

    public abstract String getEmail();

    public abstract SocialCode getSocialCode();
}

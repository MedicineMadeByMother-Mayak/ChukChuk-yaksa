package com.mayak.chuckchuck.security.oauth2.user.Naver;

import com.mayak.chuckchuck.enums.SocialCode;
import com.mayak.chuckchuck.security.oauth2.user.OAuth2UserInfo;
import lombok.ToString;

import java.util.Map;

@ToString
public class NaverOAuth2UserInfo extends OAuth2UserInfo {

    public NaverOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getId() {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        if (response == null) {
            return null;
        }
        return (String) response.get("id");
    }

    @Override
    public String getName() {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        if (response == null) {
            return null;
        }
        return (String) response.get("name");
    }

    @Override
    public String getEmail() {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        if (response == null) {
            return null;
        }
        return (String) response.get("email");
    }

    @Override
    public SocialCode getSocialCode() {
        return SocialCode.NAVER;
    }
}

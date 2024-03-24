package com.mayak.chuckchuck.oauth2.user.Kakao;

import com.mayak.chuckchuck.enums.SocialCode;
import com.mayak.chuckchuck.oauth2.user.OAuth2UserInfo;

import java.util.Map;

public class KakaoOAuth2UserInfo extends OAuth2UserInfo {

    public KakaoOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getId() {
        return "";
    }

    @Override
    public String getName() {
        Map<String, Object> properties = (Map<String, Object>) attributes.get("kakao_account");

        if (properties == null) {
            return null;
        }
        return (String) properties.get("name");
    }

    @Override
    public String getEmail() {
        Map<String, Object> properties = (Map<String, Object>) attributes.get("kakao_account");

        if (properties == null) {
            return null;
        }
        return (String) properties.get("email");
    }

    @Override
    public SocialCode getSocialCode() {
        return SocialCode.KAKAO;
    }
}

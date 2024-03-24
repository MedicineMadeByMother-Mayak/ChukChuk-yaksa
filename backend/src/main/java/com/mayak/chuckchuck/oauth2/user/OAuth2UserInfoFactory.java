package com.mayak.chuckchuck.oauth2.user;

import com.mayak.chuckchuck.oauth2.exception.OAuth2AuthenticationProcessingException;
import com.mayak.chuckchuck.oauth2.user.Google.GoogleOAuth2UserInfo;
import com.mayak.chuckchuck.oauth2.user.Kakao.KakaoOAuth2UserInfo;
import com.mayak.chuckchuck.oauth2.user.Naver.NaverOAuth2UserInfo;

import java.util.Map;

public class OAuth2UserInfoFactory {


    public static OAuth2UserInfo getOAuth2UserInfo(OAuth2Provider providerType, Map<String, Object> attributes) {
        switch (providerType) {
            case GOOGLE -> {
                return new GoogleOAuth2UserInfo(attributes);
            }
            case KAKAO -> {
                return new KakaoOAuth2UserInfo(attributes);
            }
            case NAVER -> {
                return new NaverOAuth2UserInfo(attributes);
            }
            default -> throw new IllegalArgumentException("Invalid Provider Type");
        }
    }
}
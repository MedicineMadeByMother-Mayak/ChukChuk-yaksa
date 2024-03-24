package com.mayak.chuckchuck.oauth2.user;

import com.mayak.chuckchuck.enums.SocialCode;

import java.util.Map;

public interface OAuth2UserInfo {

    SocialCode getProvider();

    String getAccessToken();

    Map<String, Object> getAttributes();

    String getId();

    String getEmail();

    String getName();

    String getFirstName();

    String getLastName();

    String getNickname();

    String getProfileImageUrl();
}
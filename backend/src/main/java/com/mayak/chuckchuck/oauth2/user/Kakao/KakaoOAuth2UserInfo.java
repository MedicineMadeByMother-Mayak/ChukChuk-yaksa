package com.mayak.chuckchuck.oauth2.user.Kakao;

import com.mayak.chuckchuck.enums.SocialCode;
import com.mayak.chuckchuck.oauth2.user.OAuth2Provider;
import com.mayak.chuckchuck.oauth2.user.OAuth2UserInfo;

import java.util.HashMap;
import java.util.Map;

public class KakaoOAuth2UserInfo implements OAuth2UserInfo {

    private final Map<String, Object> attributes;
    private final String accessToken;
    private final String id;
    private final String email;
    private final String name;
    private final String firstName;
    private final String lastName;
    private final String nickName;
    private final String profileImageUrl;

    public KakaoOAuth2UserInfo(String accessToken, Map<String, Object> attributes) {
        this.accessToken = accessToken;
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        this.attributes = kakaoAccount != null ? new HashMap<>(kakaoAccount) : new HashMap<>();

        if (this.attributes.containsKey("profile")) {
            Map<String, Object> kakaoProfile = (Map<String, Object>) this.attributes.get("profile");
            this.nickName = (String) kakaoProfile.get("nickname");
            this.profileImageUrl = (String) kakaoProfile.get("profile_image_url");
            // Ensure other attributes are transferred from kakaoProfile to attributes if necessary
        } else {
            this.nickName = null;
            this.profileImageUrl = null;
        }

        this.id = attributes.get("id").toString();
        this.email = (String) this.attributes.get("email");
        this.name = this.firstName = this.lastName = null;

        // Now safe to modify attributes as it's guaranteed to be initialized
        this.attributes.put("id", id);
        this.attributes.put("email", email);
    }

    @Override
    public SocialCode getProvider() {
        return SocialCode.KAKAO;
    }

    @Override
    public String getAccessToken() {
        return accessToken;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public String getNickname() {
        return nickName;
    }

    @Override
    public String getProfileImageUrl() {
        return profileImageUrl;
    }
}
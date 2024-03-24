package com.mayak.chuckchuck.oauth2.service;

import com.mayak.chuckchuck.domain.User;
import com.mayak.chuckchuck.oauth2.exception.OAuth2AuthenticationProcessingException;
import com.mayak.chuckchuck.oauth2.user.OAuth2UserInfo;
import com.mayak.chuckchuck.oauth2.user.OAuth2UserInfoFactory;
import com.mayak.chuckchuck.repository.UserRepository;
import com.mayak.chuckchuck.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

// 공급자로부터 받은 엑세스 토큰을 사용하여 사용자 정보를 요청한 후 실행되는 코드
@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final UserService userService;
    private final UserRepository userRepository;

    // 스프링 시큐리티 OAuth2LoginAuthenticationFilter에서 시작된 OAuth2인증 과정 중에 호출된다.
    // 호출 시점 : 액세스 토큰을 OAuth2 제공자로부터 받았을 때.
    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);

        try {
            return processOAuth2User(oAuth2UserRequest, oAuth2User);
        } catch (AuthenticationException ex) {
            throw ex;
        } catch (Exception ex) {
            // Throwing an instance of AuthenticationException will trigger the OAuth2AuthenticationFailureHandler
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }
    /**
     * Access Token을 얻고 난 후
     * 토큰으로 유저 정보 받아오기
     * 해당 이메일로 된 계정이 없는 경우 -> 회원가입
     * 해당 이메일로 된 계정이 있는 경우 -> 토큰 발급
     */
    private OAuth2User processOAuth2User(OAuth2UserRequest userRequest, OAuth2User oAuth2User) {
        String registrationId = userRequest.getClientRegistration()
                .getRegistrationId();
        String accessToken = userRequest.getAccessToken().getTokenValue();

        OAuth2UserInfo userInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(registrationId, accessToken, oAuth2User.getAttributes());
        if (!StringUtils.hasText(userInfo.getEmail())) {
            throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
        }
        // 이미 가입한 유저인지 체크
        User savedUser = userRepository.findBySocialCodeAndSocial(userInfo.getProvider(), userInfo.getEmail()).orElse(null);
        if (savedUser == null) {  // 없는 유저 회원가입 필요
            userService.join(userInfo.getProvider(), userInfo.getEmail());
        }

        return new OAuth2UserPrincipal(userInfo);
    }
}

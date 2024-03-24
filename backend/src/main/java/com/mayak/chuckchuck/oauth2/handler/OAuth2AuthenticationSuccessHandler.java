package com.mayak.chuckchuck.oauth2.handler;

import com.mayak.chuckchuck.oauth2.HttpCookieOAuth2AuthorizationRequestRepository;
import com.mayak.chuckchuck.oauth2.service.OAuth2UserPrincipal;
import com.mayak.chuckchuck.oauth2.util.CookieUtils;
import com.mayak.chuckchuck.jwt.TokenProvider;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.Optional;

import static com.mayak.chuckchuck.oauth2.HttpCookieOAuth2AuthorizationRequestRepository.REDIRECT_URI_PARAM_COOKIE_NAME;

/**
 * OAuth2 인증 성공 시 호출되는 핸들러. 처음 프론트엔드에서 백엔드로 로그인 요청시 mode 쿼리 파라미터에 담긴 값에 따라 분기하여 처리.
 * mode 값이 login이면 사용자 정보를 DB에 저장하고, 서비스 자체 액세스 토큰, 리프레시 토큰을 생성하고, 리프레시 토큰을 DB에 저장한다.
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;
    private final TokenProvider tokenProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {

        String targetUrl = determineTargetUrl(request, response, authentication);

        // 이미 전송을 보냈다면 로그 남기고 중지
        if (response.isCommitted()) {
            logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
            return;
        }

        clearAuthenticationAttributes(request, response);
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

        Optional<String> redirectUri = CookieUtils.getCookie(request, REDIRECT_URI_PARAM_COOKIE_NAME)
                .map(Cookie::getValue);

        String targetUrl = redirectUri.orElse(getDefaultTargetUrl());

        OAuth2UserPrincipal principal = getOAuth2UserPrincipal(authentication);

        if (principal == null) {
            return UriComponentsBuilder.fromUriString(targetUrl).queryParam("error", "Login failed").build().toUriString();
        }

        String accessToken = tokenProvider.createToken(authentication);
        String refreshToken = "test_refresh_token";

        return UriComponentsBuilder.fromUriString(targetUrl).queryParam("access_token", accessToken).queryParam("refresh_token", refreshToken).build().toUriString();
    }

    private OAuth2UserPrincipal getOAuth2UserPrincipal(Authentication authentication) {
        Object principal = authentication.getPrincipal();

        if (principal instanceof OAuth2UserPrincipal) {
            return (OAuth2UserPrincipal) principal;
        }
        return null;
    }

    protected void clearAuthenticationAttributes(HttpServletRequest request, HttpServletResponse response) {
        super.clearAuthenticationAttributes(request);
        httpCookieOAuth2AuthorizationRequestRepository.removeAuthorizationRequestCookies(request, response);
    }
}

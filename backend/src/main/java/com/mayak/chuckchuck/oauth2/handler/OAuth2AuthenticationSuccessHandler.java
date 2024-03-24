package com.mayak.chuckchuck.oauth2.handler;

import com.mayak.chuckchuck.config.AppProperties;
import com.mayak.chuckchuck.domain.User;
import com.mayak.chuckchuck.enums.RoleType;
import com.mayak.chuckchuck.exception.ErrorCode.CommonErrorCode;
import com.mayak.chuckchuck.exception.RestApiException;
import com.mayak.chuckchuck.jwt.AuthToken;
import com.mayak.chuckchuck.oauth2.HttpCookieOAuth2AuthorizationRequestRepository;
import com.mayak.chuckchuck.oauth2.user.BoardPrincipal;
import com.mayak.chuckchuck.oauth2.user.OAuth2Provider;
import com.mayak.chuckchuck.oauth2.user.OAuth2UserInfo;
import com.mayak.chuckchuck.oauth2.user.OAuth2UserInfoFactory;
import com.mayak.chuckchuck.oauth2.util.CookieUtils;
import com.mayak.chuckchuck.jwt.AuthTokenProvider;
import com.mayak.chuckchuck.repository.UserRepository;
import com.mayak.chuckchuck.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import static com.mayak.chuckchuck.oauth2.HttpCookieOAuth2AuthorizationRequestRepository.REDIRECT_URI_PARAM_COOKIE_NAME;
import static org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames.REFRESH_TOKEN;

/**
 * OAuth2 인증 성공 시 호출되는 핸들러. 처음 프론트엔드에서 백엔드로 로그인 요청시 mode 쿼리 파라미터에 담긴 값에 따라 분기하여 처리.
 * mode 값이 login이면 사용자 정보를 DB에 저장하고, 서비스 자체 액세스 토큰, 리프레시 토큰을 생성하고, 리프레시 토큰을 DB에 저장한다.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final AuthTokenProvider authTokenProvider;
    private final AppProperties appProperties;
    private final UserRepository userRepository;
    private final HttpCookieOAuth2AuthorizationRequestRepository authorizationRequestBasedOnCookieRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        String targetUrl = determineTargetUrl(request, response, authentication);

        if (response.isCommitted()) {
            logger.debug("Response has already bean committed. Unable to redirect to " + targetUrl);
            return;
        }

        clearAuthenticationAttributes(request, response);
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        Optional<String> redirectUri = CookieUtils.getCookie(request, REDIRECT_URI_PARAM_COOKIE_NAME)
                .map(Cookie::getValue);
        if (redirectUri.isPresent() && !isAuthorizedRedirectUri(redirectUri.get())) {
            throw new IllegalArgumentException("인증되지 않은 redirect URI");
        }

        String targetUrl = redirectUri.orElse(getDefaultTargetUrl());

        OAuth2AuthenticationToken authToken = (OAuth2AuthenticationToken) authentication;
        OAuth2Provider providerType = OAuth2Provider.valueOf(authToken.getAuthorizedClientRegistrationId().toUpperCase());

        // OAuth2UserInfo를 사용하여 사용자 정보 추출
        OAuth2UserInfo userInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(providerType, authToken.getPrincipal().getAttributes());
        Collection<? extends GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(RoleType.USER.getCode()));
        RoleType roleType = hasAuthority(authorities, RoleType.ADMIN.getCode()) ? RoleType.ADMIN : RoleType.USER;

        AuthToken accessToken = authTokenProvider.createAuthToken(userInfo.getEmail(), roleType.getCode(), appProperties.getAuth().getTokenExpiry());

        // AuthToken 생성
        long refreshTokenExpiry = appProperties.getAuth().getRefreshTokenExpiry();
        AuthToken refreshToken = authTokenProvider.createAuthToken(appProperties.getAuth().getTokenSecret(), refreshTokenExpiry);

        // 사용자 정보 DB에 저장 및 리프레시 토큰 업데이트
        User user = userRepository.findBySocialCodeAndSocial(userInfo.getSocialCode(), userInfo.getEmail())
                .orElseThrow(() -> new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND));
        user.updateToken(refreshToken.getToken());
        userRepository.saveAndFlush(user);
        log.debug("리프레시 토큰 {} : {}", userInfo, user.getToken());

        // 쿠키 설정
        int cookieMaxAge = (int) refreshTokenExpiry / 60;
        CookieUtils.deleteCookie(request, response, REFRESH_TOKEN);
        CookieUtils.addCookie(response, REFRESH_TOKEN, refreshToken.getToken(), cookieMaxAge);

        return UriComponentsBuilder.fromUriString(targetUrl)
                .queryParam("token", accessToken.getToken())
                .build().toUriString();
    }

    private boolean isAuthorizedRedirectUri(String uri) {
        URI clientRedirectUri = URI.create(uri);
        return appProperties.getOAuth2().getAuthorizedRedirectUris()
                .stream()
                .anyMatch(authorizedRedirectUri -> {
                    URI authorizeURI = URI.create(authorizedRedirectUri);
                    return authorizeURI.getHost().equalsIgnoreCase(clientRedirectUri.getHost()) && authorizeURI.getPort() == clientRedirectUri.getPort();
                });
    }

    private boolean hasAuthority(Collection<? extends GrantedAuthority> authorities, String authority) {
        if (authorities == null) {
            return false;
        }

        for (GrantedAuthority grantedAuthority : authorities) {
            if (authority.equals(grantedAuthority.getAuthority())) {
                return true;
            }
        }
        return false;
    }

    protected void clearAuthenticationAttributes(HttpServletRequest request, HttpServletResponse response) {
        super.clearAuthenticationAttributes(request);
        authorizationRequestBasedOnCookieRepository.removeAuthorizationRequestCookies(request, response);
    }
}

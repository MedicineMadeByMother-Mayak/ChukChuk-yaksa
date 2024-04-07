package com.mayak.chuckchuck.security.config;

import com.mayak.chuckchuck.config.AppProperties;
import com.mayak.chuckchuck.config.CorsProperties;
import com.mayak.chuckchuck.security.jwt.AuthTokenProvider;
import com.mayak.chuckchuck.security.jwt.JwtAuthorizationFilter;
import com.mayak.chuckchuck.security.oauth2.HttpCookieOAuth2AuthorizationRequestRepository;
import com.mayak.chuckchuck.security.oauth2.handler.OAuth2AuthenticationFailureHandler;
import com.mayak.chuckchuck.security.oauth2.handler.OAuth2AuthenticationSuccessHandler;
import com.mayak.chuckchuck.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@RequiredArgsConstructor
public class AuthenticationConfig {

    private final AuthTokenProvider tokenProvider;
    private final CorsProperties corsProperties;
    private final AppProperties appProperties;
    private final UserRepository userRepository;

    @Bean
    public BCryptPasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 토큰 필터 설정
     */
    @Bean
    public JwtAuthorizationFilter tokenAuthenticationFilter() {
        return new JwtAuthorizationFilter(tokenProvider);
    }

    /**
     * 쿠키 기반 인가 Repository
     * 인가응답을 연계하고 검증할 때 사용
     */
    @Bean
    public HttpCookieOAuth2AuthorizationRequestRepository oAuth2AuthorizationRequestBasedOnCookieRepository() {
        return new HttpCookieOAuth2AuthorizationRequestRepository();
    }

    /**
     * OAuth 인증 성공 핸들러
     *
     * @return
     */
    @Bean
    public OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler() {
        return new OAuth2AuthenticationSuccessHandler(
                tokenProvider,
                appProperties,
                userRepository,
                oAuth2AuthorizationRequestBasedOnCookieRepository()
        );
    }

    /**
     * OAuth 인증 실패 핸들러
     */
    @Bean
    public OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler() {
        return new OAuth2AuthenticationFailureHandler(oAuth2AuthorizationRequestBasedOnCookieRepository());
    }

    /**
     * Cors 설정
     */
    @Bean
    public UrlBasedCorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();

        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedHeaders(Arrays.asList(corsProperties.getAllowedHeaders().split(",")));
        corsConfig.setAllowedMethods(Arrays.asList(corsProperties.getAllowedMethods().split(",")));
        corsConfig.setAllowedOrigins(Arrays.asList(corsProperties.getAllowedOrigins().split(",")));
        corsConfig.addAllowedOriginPattern("*");
        corsConfig.setAllowCredentials(true);
        corsConfig.setMaxAge(corsConfig.getMaxAge());

        corsConfigurationSource.registerCorsConfiguration("/**", corsConfig);
        return corsConfigurationSource;
    }
}

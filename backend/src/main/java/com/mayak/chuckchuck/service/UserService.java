package com.mayak.chuckchuck.service;

import com.mayak.chuckchuck.config.AppProperties;
import com.mayak.chuckchuck.domain.User;
import com.mayak.chuckchuck.dto.request.UserInfoRequest;
import com.mayak.chuckchuck.dto.response.UserDefaultResponse;
import com.mayak.chuckchuck.dto.response.UserInfoResponse;
import com.mayak.chuckchuck.dto.response.UserLoginResponse;
import com.mayak.chuckchuck.enums.RoleType;
import com.mayak.chuckchuck.enums.SocialCode;
import com.mayak.chuckchuck.exception.ErrorCode.CommonErrorCode;
import com.mayak.chuckchuck.exception.ErrorCode.TokenErrorCode;
import com.mayak.chuckchuck.exception.RestApiException;
import com.mayak.chuckchuck.repository.UserRepository;
import com.mayak.chuckchuck.security.jwt.AuthToken;
import com.mayak.chuckchuck.security.jwt.AuthTokenProvider;
import com.mayak.chuckchuck.security.oauth2.util.CookieUtils;
import com.mayak.chuckchuck.security.oauth2.util.HeaderUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final AuthTokenProvider tokenProvider;
    private final AppProperties appProperties;
    public static final String REFRESH_TOKEN = "refresh_token";

    //리프레쉬 토큰으로 엑세스토큰 재발급
    public String accessToken(String email,HttpServletRequest request) {
        //access토큰 찾기
        String accessToken = HeaderUtils.getAccessToken(request);
        //refresh토큰 찾기
        String refreshToken = CookieUtils.getCookie(request, REFRESH_TOKEN)
                .map(Cookie::getValue)
                .orElse(null);
        AuthToken authRefreshToken = tokenProvider.convertAuthToken(refreshToken);

        if (!authRefreshToken.validate()) {
            throw new RestApiException(TokenErrorCode.INVALID_REFRESH_TOKEN);
        }

        //새로운 access토큰 생성

        AuthToken newAccessToken = tokenProvider.createAuthToken(email, RoleType.USER.getCode(), SocialCode.GOOGLE, appProperties.getAuth().getTokenExpiry());
        return newAccessToken.getToken();
    }


    /**
     * 리프레시 토큰
     * 1. 액세스 토큰 기존 헤더에서 가져오기
     * 2. 액세스 토큰 (String)-> (Token)으로 변환
     * 3. 유효한 토큰인지 검증
     * 4. 만료된 토큰인지 검증
     * 5. claims에서 이메일 가져오기
     * 6. Claims에서 role타입 가져오기
     * 7. 프론트로 부터 쿠키에서 리프레시 토큰 가져오기
     * 8. 유효한지 확인
     * 9. 유저-리프레시 레포에서 토큰 가져오기 - 없을 경우 에러 - 로그인할 때 만들어지기 때문에 없을리 없음
     * 10. 새로운 액세스 토큰 발급
     * 11. 리프레시 토큰이 3일 이하로 남았을 경우 리프레시 토큰 갱신
     * 12. DB에 업데이트
     * 13. 액세스 토큰 리턴
     */
    public UserLoginResponse refreshToken(String email, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("getName : "+email);
        // 1. 헤더로 부터 액세스 토큰 가져오기
        String accessToken = HeaderUtils.getAccessToken(request);
        AuthToken authToken = tokenProvider.convertAuthToken(accessToken);
        User user = userRepository.findBySocialCodeAndEmail(SocialCode.GOOGLE, email).orElseThrow(() ->
                new RestApiException(TokenErrorCode.USER_NOT_FOUND)
        );
        UserDefaultResponse defaultResponse = UserDefaultResponse.fromUser(user);

        // 2-1. 토큰이 유효한지 체크
        if (authToken.validate()) {   // 유효하다면 지금 토큰 그대로 반환
            return new UserLoginResponse(defaultResponse, authToken.getToken());
        }

        // 2-2. 토큰이 유효하지 않다면 리프레시 토큰이 있는지 확인하자
        Claims claims = authToken.getExpiredClaims();  // 만료되었을 경우 만료 토큰을 가져옴.
        if (claims == null) {
            return new UserLoginResponse(defaultResponse, accessToken);  // 아직 만료 안됨
        }

        RoleType roleType = RoleType.of(claims.get("role", String.class));

        //refresh token
        String refreshToken = CookieUtils.getCookie(request, REFRESH_TOKEN)
                .map(Cookie::getValue)
                .orElse(null);
        AuthToken authRefreshToken = tokenProvider.convertAuthToken(refreshToken);

        if (!authRefreshToken.validate()) {
            throw new RestApiException(TokenErrorCode.INVALID_REFRESH_TOKEN);
        }

        // DB 확인
        String reshToken = user.getToken();
        if (reshToken == null) {
            throw new RestApiException(TokenErrorCode.INVALID_REFRESH_TOKEN);
        }

        Date now = new Date();
        AuthToken newAccessToken = tokenProvider.createAuthToken(user.getUserName(), roleType.getCode(), user.getSocialCode(),appProperties.getAuth().getTokenExpiry());

        long validTime = authRefreshToken.extractClaims().getExpiration().getTime() - now.getTime();

        //refresh 토큰 기간이 3일 이하일 경우 새로 갱신
        if (validTime <= 3) {
            authRefreshToken = tokenProvider.createAuthToken(
                    appProperties.getAuth().getTokenSecret(),
                    appProperties.getAuth().getRefreshTokenExpiry()
            );
            user.updateToken(authRefreshToken.getToken());

            int cookieMaxAge = (int) appProperties.getAuth().getRefreshTokenExpiry() / 60;
            CookieUtils.deleteCookie(request, response, REFRESH_TOKEN);
            CookieUtils.addCookie(response, REFRESH_TOKEN, authRefreshToken.getToken(), cookieMaxAge);
        }
        return new UserLoginResponse(defaultResponse, newAccessToken.getToken());
    }

    /**
     * User의 기타 개인정보를 처음 등록(업데이트로 동작)
     *
     * @author: 최서현
     * @param: userInfoRequest
     */
    public void updateUserInfo(User user, UserInfoRequest userInfoRequest) {
        user.updateInfo(userInfoRequest);
    }

    /**
     * User의 기타 개인정보를 조회
     *
     * @author: 차현철
     * @param: User
     */
    public UserInfoResponse getUserInfo(User user) {
        return UserInfoResponse.fromEntity(user);
    }

    // 회원가입 - 소셜로그인
    public void join(SocialCode socialCode, String email) {
        checkEmailExistenceOrException(socialCode, email);
        User user = User.of(socialCode, email);
        userRepository.save(user);
    }

    // 동일한 유저가 이미 있으면 에러 아니면 진행.
    private void checkEmailExistenceOrException(SocialCode socialCode, String email) {
        userRepository.findBySocialCodeAndEmail(socialCode, email).ifPresent(it -> {
            throw new RestApiException(CommonErrorCode.DUPLICATED_USER_EMAIL);
        });
    }


}

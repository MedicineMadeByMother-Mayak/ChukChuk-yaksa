package com.mayak.chuckchuck.service;

import com.mayak.chuckchuck.domain.User;
import com.mayak.chuckchuck.dto.request.UserInfoRequest;
import com.mayak.chuckchuck.dto.response.UserInfoResponse;
import com.mayak.chuckchuck.enums.SocialCode;
import com.mayak.chuckchuck.exception.ErrorCode.CommonErrorCode;
import com.mayak.chuckchuck.exception.RestApiException;
import com.mayak.chuckchuck.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    /**
     * User의 기타 개인정보를 처음 등록(업데이트로 동작)
     *
     * @author: 최서현
     * @param: userInfoRequest
     */
    public void updateUserInfo(UserInfoRequest userInfoRequest) {
        //== 임시User객체
        User user = userRepository.findById(1L).get();
        //==
        user.updateInfo(userInfoRequest);
    }

    /**
     * User의 기타 개인정보를 조회
     *
     * @author: 김태완
     * @param: return: UserInfoResponse
     */
    public UserInfoResponse getUserInfo() {
        User user = userRepository.findById(1L).get();
        return UserInfoResponse.fromEntity(user);
    }

    // 회원가입 - 소셜로그인
    @org.springframework.transaction.annotation.Transactional
    public User join(SocialCode socialCode, String email) {
        checkEmailExistenceOrException(socialCode, email);
        User user = User.of(socialCode, email);
        userRepository.save(user);
        return user;
    }

    // 동일한 유저가 이미 있으면 에러 아니면 진행.
    private void checkEmailExistenceOrException(SocialCode socialCode, String email) {
        userRepository.findBySocialCodeAndSocial(socialCode, email).ifPresent(it -> {
            throw new RestApiException(CommonErrorCode.DUPLICATED_USER_EMAIL);
        });
    }
}

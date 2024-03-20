package com.mayak.chuckchuck.service;

import com.mayak.chuckchuck.domain.User;
import com.mayak.chuckchuck.dto.request.UserInfoRequest;
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
     * @author: 최서현
     * @param: userInfoRequest
     */
    public void updateUserInfo(UserInfoRequest userInfoRequest) {
        //== 임시User객체
        User user = userRepository.findById(1L).get();
        //==
        user.updateInfo(userInfoRequest);
    }

}

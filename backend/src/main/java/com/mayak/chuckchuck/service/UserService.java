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
     *
     * @param userInfoRequest
     */
    public void registUserInfo(UserInfoRequest userInfoRequest) {
        System.out.println("user 찾을게요");
        User user = userRepository.findById(1L).get();
        System.out.println("user 찾았고요 : "+user.getSex());
        user.updateInfo(userInfoRequest);
    }
}

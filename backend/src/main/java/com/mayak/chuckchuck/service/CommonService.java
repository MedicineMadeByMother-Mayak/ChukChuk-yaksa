package com.mayak.chuckchuck.service;

import com.mayak.chuckchuck.domain.User;
import com.mayak.chuckchuck.exception.ErrorCode.CommonErrorCode;
import com.mayak.chuckchuck.exception.RestApiException;
import com.mayak.chuckchuck.security.oauth2.user.UserPrincipal;
import com.mayak.chuckchuck.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class CommonService {
    private final UserRepository userRepository;

    public User getUserOrException(UserPrincipal principal) {
        return userRepository.findBySocialCodeAndEmail(principal.socialCode(), principal.email()).orElseThrow(() ->
                new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND));
    }
}

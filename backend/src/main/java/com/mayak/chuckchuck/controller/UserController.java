package com.mayak.chuckchuck.controller;

import com.mayak.chuckchuck.dto.request.UserInfoRequest;
import com.mayak.chuckchuck.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/profile")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    /**
     * 사용자 정보 조회
     * @author:
     * @param:
     * @return:
     */

    /**
     * 사용자 정보 초기 등록(업데이트로 동작)
     * @author: 최서현
     * @param: userInfoRequest
     * @return:
     */
    @PostMapping("/")
    public ResponseEntity<Void> registUserInfo(@Valid @RequestBody UserInfoRequest userInfoRequest){
        userService.registUserInfo(userInfoRequest);
        return ResponseEntity.ok().build();
    }

    /**
     * 사용자 정보 수정
     * @author:
     * @param:
     * @return:
     */
}

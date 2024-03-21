package com.mayak.chuckchuck.controller;

import com.mayak.chuckchuck.dto.request.UserInfoRequest;
import com.mayak.chuckchuck.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/profile")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    /**
     * 사용자 정보 조회
     * @author:김태완
     * @param:
     * @return:
     */

    /**
     * 사용자 정보 초기 등록(업데이트로 동작)
     * @author: 최서현
     * @param: userInfoRequest
     */
    @PostMapping("")
    public ResponseEntity<Void> registUserInfo(@Valid @RequestBody UserInfoRequest userInfoRequest){
        userService.updateUserInfo(userInfoRequest);
        return ResponseEntity.ok().build();
    }

    /**
     * 사용자 정보 수정
     * @author: 최서현
     * @param: userInfoRequest
     */
    @PutMapping("")
    public ResponseEntity<Void> updateUserInfo(@Valid @RequestBody UserInfoRequest userInfoRequest){
        userService.updateUserInfo(userInfoRequest);
        return ResponseEntity.ok().build();
    }

}

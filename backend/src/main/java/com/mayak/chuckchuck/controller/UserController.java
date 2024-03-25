package com.mayak.chuckchuck.controller;

import com.mayak.chuckchuck.domain.User;
import com.mayak.chuckchuck.dto.request.UserInfoRequest;
import com.mayak.chuckchuck.dto.response.UserInfoResponse;
import com.mayak.chuckchuck.security.oauth2.user.UserPrincipal;
import com.mayak.chuckchuck.service.CommonService;
import com.mayak.chuckchuck.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/profile")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final CommonService commonService;
    /**
     * 사용자 정보 조회
     * @author: 차현철
     * @param: principal
     * @return: ResponseEntity
     */
    @GetMapping("")
    public ResponseEntity getUserInfo(@AuthenticationPrincipal UserPrincipal principal) {
        User user = commonService.getUserOrException(principal);
        UserInfoResponse userInfoResponse = userService.getUserInfo(user);
        return ResponseEntity.ok(userInfoResponse);
    }

    /**
     * 사용자 정보 초기 등록(업데이트로 동작)
     * @author: 최서현
     * @param: userInfoRequest
     */
    @PostMapping("")
    public ResponseEntity<Void> registUserInfo(@AuthenticationPrincipal UserPrincipal principal, @Valid @RequestBody UserInfoRequest userInfoRequest){
        User user = commonService.getUserOrException(principal);
        userService.updateUserInfo(user, userInfoRequest);
        return ResponseEntity.ok().build();
    }

    /**
     * 사용자 정보 수정
     * @author: 최서현
     * @param: userInfoRequest
     */
    @PutMapping("")
    public ResponseEntity<Void> updateUserInfo(@AuthenticationPrincipal UserPrincipal principal, @Valid @RequestBody UserInfoRequest userInfoRequest){
        User user = commonService.getUserOrException(principal);
        userService.updateUserInfo(user, userInfoRequest);
        return ResponseEntity.ok().build();
    }
}

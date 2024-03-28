package com.mayak.chuckchuck.controller;

import com.mayak.chuckchuck.domain.User;
import com.mayak.chuckchuck.dto.request.UserInfoRequest;
import com.mayak.chuckchuck.dto.response.UserInfoResponse;
import com.mayak.chuckchuck.dto.response.UserLoginResponse;
import com.mayak.chuckchuck.security.oauth2.user.UserPrincipal;
import com.mayak.chuckchuck.service.CommonService;
import com.mayak.chuckchuck.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final CommonService commonService;

    // 리프레쉬토큰을 통해 액세스토큰을 재발급하는 코드(디벨롭필요)
//    @GetMapping("/users/access")
//    public ResponseEntity<String> access(Authentication authentication, HttpServletRequest request) {
//        System.out.println("너 뭔데??? "+authentication.getName());
//        return ResponseEntity.ok(userService.accessToken(authentication.getName(),request));
//    }


    // 액세스토큰이 만료되었을경우 리프레쉬토큰을 통해 재발급(리프레쉬토큰 만료되었을때 이것도 재발급 하는 로직 포함)
//    @GetMapping("/users/refresh")
//    public ResponseEntity<UserLoginResponse> refresh(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
//        return ResponseEntity.ok(userService.refreshToken(authentication.getName(), request, response));
//    }

    /**
     * 사용자 정보 조회
     * @author: 차현철
     * @param: principal
     * @return: ResponseEntity
     */
    @GetMapping("/profile")
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
    @PostMapping("/profile")
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
    @PutMapping("/profile")
    public ResponseEntity<Void> updateUserInfo(@AuthenticationPrincipal UserPrincipal principal, @Valid @RequestBody UserInfoRequest userInfoRequest){
        User user = commonService.getUserOrException(principal);
        userService.updateUserInfo(user, userInfoRequest);
        return ResponseEntity.ok().build();
    }
}

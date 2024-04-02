package com.mayak.chuckchuck.controller;

import com.mayak.chuckchuck.domain.User;
import com.mayak.chuckchuck.dto.request.*;
import com.mayak.chuckchuck.dto.response.UserPillEffectListAndSearchResponse;
import com.mayak.chuckchuck.dto.response.UserPillEffectResponse;
import com.mayak.chuckchuck.dto.response.UserPillSideEffectListResponse;
import com.mayak.chuckchuck.security.oauth2.user.UserPrincipal;
import com.mayak.chuckchuck.service.CommonService;
import com.mayak.chuckchuck.service.TagService;
import com.mayak.chuckchuck.service.UserPillEffectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/effects")
@RequiredArgsConstructor
public class UserPillEffectController {
    private final UserPillEffectService userPillEffectService;
    private final TagService tagService;
    private final CommonService commonService;

    /**
     * 약효기록 리스트 조회 및 검색
     * @author 최진학
     * @param:
     * @return userPillSideEffectListResponse(부작용 리스트) or userPillEffectListAndSearchResponse(조회 및 검색)
     */
//    public ResponseEntity<Object> getUserPillEffectListAndSearch(@AuthenticationPrincipal UserPrincipal principal,
//                                                                 @PathVariable Long categoryId,
//                                                                 @PathVariable String keyword,
//                                                                 @PathVariable String page) {
    @GetMapping("")
    public ResponseEntity<Object> getUserPillEffectListAndSearch(@AuthenticationPrincipal UserPrincipal principal,
                                                                 @RequestParam("categoryid") Long categoryId,
                                                                 @RequestParam("keyword") String keyword,
                                                                 @RequestParam("page") String page) {
        User user = commonService.getUserOrException(principal);
        UserPillEffectListAndSearchRequest userPillEffectListAndSearchRequest = new UserPillEffectListAndSearchRequest(categoryId, keyword, page);


        // if   : 페이징 X, 문진표 - 부작용 리스트 조회
        // else : 페이징 O, 약효 기록 리스트 조회 or 검색
        if (userPillEffectListAndSearchRequest.page() == null) {
            UserPillSideEffectListResponse userPillSideEffectListResponse
                    = userPillEffectService.getUserPillSideEffectList(user);

            return ResponseEntity.ok(userPillSideEffectListResponse);
        } else {
            UserPillEffectListAndSearchResponse userPillEffectListAndSearchResponse
                    = userPillEffectService.getUserPillEffectListAndSearch(user, userPillEffectListAndSearchRequest);

            return ResponseEntity.ok(userPillEffectListAndSearchResponse);
        }
    }


    /**
     * 약효기록 상세조회
     * @author 최진학
     * @param pillId
     * @return UserPillEffectResponse
     */
    @GetMapping("/pill/{pillId}")
    public ResponseEntity<UserPillEffectResponse> registUserPillEffect(@AuthenticationPrincipal UserPrincipal principal, @PathVariable Long pillId) {
        User user = commonService.getUserOrException(principal);
        UserPillEffectResponse userPillEffectResponse = userPillEffectService.registUserPillEffect(user, pillId);

        return ResponseEntity.ok(userPillEffectResponse);
    }

    /**
     * 기존태그 사용하기
     * @author 최서현
     * @param
     */
    @PostMapping("/pill/tag")
    public ResponseEntity<Void> useRegistedTag(@AuthenticationPrincipal UserPrincipal principal, @RequestBody UseRegistedTagRequest request) {
        User user = commonService.getUserOrException(principal);
        userPillEffectService.useRegistedTag(user, request);

        return ResponseEntity.ok().build();
    }

    /**
     * 약효기록 추가 (있으면 가져오기, 없으면 추가)
     * @author 최진학
     * @param:
     * @return:
     */
    @PostMapping("/")
    public ResponseEntity<Void> registUserPillEffect(@AuthenticationPrincipal UserPrincipal principal, @RequestBody UserPillEffectRegistInfoRequest userPillEffectRegistInfoRequest) {
        User user = commonService.getUserOrException(principal);
        userPillEffectService.updateUserPillEffect(user, userPillEffectRegistInfoRequest);

        return ResponseEntity.ok().build();
    }

    /**
     * 약효기록 삭제
     * @author 최진학
     * @param userPillEffectId (약효 효과 ID)
     * @return HttpStatus.OK
     */
    @DeleteMapping("/pill/{userPillEffectId}")
    public ResponseEntity<Void> updateUserPillEffectIsDelete(@PathVariable Long userPillEffectId){
        userPillEffectService.updateUserPillEffectIsDelete(userPillEffectId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 약효기록 새로운 태그 등록
     * @author:
     * @param:
     * @return:
     */
    @PostMapping("/tag")
    public ResponseEntity<Long> registTag(@AuthenticationPrincipal UserPrincipal principal,@Valid @RequestBody RegistTagRequest registTagRequest) {
        User user = commonService.getUserOrException(principal);
        Long id = tagService.reigstTag(user, registTagRequest.tagName(), registTagRequest.categoryId());

        return ResponseEntity.ok(id);
    }

    /**
     * 약효기록 약효에 태그 추가 (약효 기록 상세조횡에 기능 추가 완료)
     * @author 최진학
     * @param:
     * @return:
     */


    /**
     * 약효기록 - 메모수정
     * @author 최진학
     * @param userPillEffectMemoRequest (메모 수정 request)
     * @return 200 OK
     */
    @PutMapping("/pill/memo")
    public ResponseEntity<Void> updateUserPillEffectIsDelete(@Valid @RequestBody UserPillEffectMemoRequest userPillEffectMemoRequest){
        userPillEffectService.updateUserPillEffectMemo(userPillEffectMemoRequest);

        return ResponseEntity.ok().build();
    }
}

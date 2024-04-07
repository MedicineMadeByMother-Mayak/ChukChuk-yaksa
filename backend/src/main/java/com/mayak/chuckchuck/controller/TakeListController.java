package com.mayak.chuckchuck.controller;


import com.mayak.chuckchuck.domain.User;
import com.mayak.chuckchuck.dto.request.*;
import com.mayak.chuckchuck.dto.request.AlarmRequest;
//import com.mayak.chuckchuck.dto.request.TakeListRequest;
import com.mayak.chuckchuck.dto.response.ActiveAlarmListResponse;
import com.mayak.chuckchuck.dto.response.ChukChukAdviceResponse;
import com.mayak.chuckchuck.security.oauth2.user.UserPrincipal;
import com.mayak.chuckchuck.service.CommonService;
import com.mayak.chuckchuck.service.TakeListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/take-list")
@RequiredArgsConstructor
public class TakeListController {
    private final CommonService commonService;
    private final TakeListService takeListService;

    /**
     * 복용리스트 조회
     * @author:김보경
     * @param:
     * @return:TakeListResponse
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getTakeList(@AuthenticationPrincipal UserPrincipal principal, @RequestParam("period") Boolean period) {
        User user = commonService.getUserOrException(principal);
        return new ResponseEntity<>(takeListService.getTakeList(user, period), HttpStatus.OK);
    }
    /**
     * 복용리스트 약 추가
     * @author:김보경
     * @param: takeListId
     * @return:200ok
     */
    @PostMapping("/{takeListId}")
    public ResponseEntity<Void> addPillsToTakeListWithId(@AuthenticationPrincipal UserPrincipal principal, @PathVariable Long takeListId, @RequestBody AddPillsToTakeListRequest addPillsToTakeListRequest) {
        User user = commonService.getUserOrException(principal);
        takeListService.addPillsToTakeList(user, takeListId, addPillsToTakeListRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    
    @PostMapping
    public ResponseEntity<Void> addPillsToTakeListWithoutId(@AuthenticationPrincipal UserPrincipal principal, @RequestBody AddPillsToTakeListRequest addPillsToTakeListRequest) {
        User user = commonService.getUserOrException(principal);
        takeListService.addPillsToTakeList(user, null, addPillsToTakeListRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * 복용리스트 이름 수정
     * @author:김보경
     * @param:takeListId
     * @return:200ok
     */
    @PutMapping("/{takeListId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> updateTakeListName(@PathVariable(value="takeListId") Long takeListId, @RequestBody UpdateTakeListRequest updateTakeListRequest) {
        takeListService.updateTakeListName(takeListId, updateTakeListRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * 복용리스트 약 삭제
     * @author:김보경
     * @param:takeListId, pillId
     * @return:200ok
     */
    @DeleteMapping("/{takeListId}/{pillId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> deleteTakeListPill(@PathVariable(value = "takeListId") Long takeListId, @PathVariable(value="pillId") Long pillId) {
        takeListService.deleteTakeListPill(takeListId, pillId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * 복용리스트 완료
     * @author: 김보경
     * @param: takeListId
     * @return: 200ok
     */
    @PutMapping("/{takeListId}/finish")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> finishTakeList(@PathVariable(value="takeListId") Long takeListId) {
        takeListService.finishTakeList(takeListId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * 복용리스트 삭제
     * @author:김보경
     * @param:takeListId
     * @return:200ok
     */
    @DeleteMapping("/{takeListId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> deleteTakeList(@PathVariable(value="takeListId") Long takeListId) {
        takeListService.deleteTakeList(takeListId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    /**
     * 알람리스트 조회
     * @author: 최서현
     * @param:
     * @return:
     */
    @GetMapping("/alarms")
    public ResponseEntity<ActiveAlarmListResponse> getAlarmList(@AuthenticationPrincipal UserPrincipal principal, @RequestParam("isAlarm") Boolean isAlarm){
        User user = commonService.getUserOrException(principal);
        ActiveAlarmListResponse activeAlarmListResponse = takeListService.getAlarmList(user, isAlarm);
        return ResponseEntity.ok(activeAlarmListResponse);
    }

    /**
     * 척척약사 조언 리스트 조회
     * @author 최진학
     * @param
     * @return
     */
    @GetMapping("/advice")
    public ResponseEntity<ChukChukAdviceResponse> getChukChukAdvice(@AuthenticationPrincipal UserPrincipal principal){
        User user = commonService.getUserOrException(principal);
        ChukChukAdviceResponse chukChukAdviceResponse = takeListService.getChukChukAdvice(user);

        return ResponseEntity.ok(chukChukAdviceResponse);
    }

    /**
     * 알람등록
     * @author: 차현철
     * @param: AlarmRequest
     * @return: ResponseEntity<Void>
     */
    @PostMapping("/alarms/{takeListId}")
    public ResponseEntity<Void> addAlarm(@PathVariable Long takeListId, @RequestBody AlarmRequest alarmRequest){
        takeListService.updateAlarm(takeListId, alarmRequest.alarmTime(), alarmRequest.cycle());
        return ResponseEntity.ok().build();
    }

    /**
     * 알람수정
     * @author: 차현철
     * @param: AlarmRequest
     * @return: ResponseEntity<Void>
     */
    @PutMapping("/alarms/{takeListId}")
    public ResponseEntity<Void> updateAlarm(@PathVariable Long takeListId, @RequestBody AlarmRequest alarmRequest){
        takeListService.updateAlarm(takeListId, alarmRequest.alarmTime(), alarmRequest.cycle());
        return ResponseEntity.ok().build();
    }

    /**
     * 알람 비활성화
     * @author: 최서현
     * @param: takeListId
     * @return:
     */
    @DeleteMapping("/alarms/{takeListId}")
    public ResponseEntity<Void> deactivateAlarm(@PathVariable("takeListId") Long takeListId){
        takeListService.updateIsAlarmFalse(takeListId);
        return ResponseEntity.ok().build();
    }
}
package com.mayak.chuckchuck.controller;


import com.mayak.chuckchuck.dto.request.*;
import com.mayak.chuckchuck.dto.request.AlarmRequest;
import com.mayak.chuckchuck.dto.request.TakeListRequest;
import com.mayak.chuckchuck.dto.response.ActiveAlarmListResponse;
import com.mayak.chuckchuck.dto.response.ChukChukAdviceResponse;
import com.mayak.chuckchuck.service.TakeListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/take-list")
@RequiredArgsConstructor
public class TakeListController {
    private final TakeListService takeListService;

    /**
     * 복용리스트 조회
     * @author:김보경
     * @param:
     * @return:TakeListResponse
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getTakeList(@RequestBody TakeListRequest takeListRequest) {
        return new ResponseEntity<>(takeListService.getTakeList(takeListRequest), HttpStatus.OK);
    }
    
    /**
     * 복용리스트 약 추가
     * @author:김보경
     * @param: takeListId
     * @return:200ok
     */
    @PostMapping("/{takeListId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> addPillsToTakeList(@PathVariable(value="takeListId") Long takeListId, @RequestBody AddPillsToTakeListRequest addPillsToTakeListRequest){
        takeListService.addPillsToTakeList(takeListId, addPillsToTakeListRequest);
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
     * 복용리스트 조회
     * @author:
     * @param:
     * @return:
     */

    /**
     * 알람리스트 조회
     * @author: 최서현
     * @param:
     * @return:
     */
    @GetMapping("/alarms")
    public ResponseEntity<ActiveAlarmListResponse> getAlarmList(){
        ActiveAlarmListResponse activeAlarmListResponse = takeListService.getAlarmList();
        return ResponseEntity.ok(activeAlarmListResponse);
    }

    /**
     * 척척약사 조언 리스트 조회
     * @author 최진학
     * @param
     * @return
     */
    @GetMapping("/advice")
    public ResponseEntity<ChukChukAdviceResponse> getChukChukAdvice(){
        ChukChukAdviceResponse chukChukAdviceResponse = takeListService.getChukChukAdvice();

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
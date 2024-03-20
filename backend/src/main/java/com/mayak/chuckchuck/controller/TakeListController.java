package com.mayak.chuckchuck.controller;


import com.mayak.chuckchuck.dto.response.ActiveAlarmListResponse;
import com.mayak.chuckchuck.service.TakeListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/take-list")
@RequiredArgsConstructor
public class TakeListController {
    private final TakeListService takeListService;
    /**
     * 복용리스트 조회
     * @author:
     * @param:
     * @return:
     */
    
    /**
     * 복용리스트 약 추가
     * @author:
     * @param:
     * @return:
     */

    /**
     * 복용리스트 이름 수정
     * @author:
     * @param:
     * @return:
     */

    /**
     * 복용리스트 약 삭제
     * @author:
     * @param:
     * @return:
     */

    /**
     * 복용리스트 완료
     * @author:
     * @param:
     * @return:
     */

    /**
     * 복용리스트 삭제
     * @author:
     * @param:
     * @return:
     */

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
     * @author:
     * @param:
     * @return:
     */

    /**
     * 알람등록
     * @author:
     * @param:
     * @return:
     */

    /**
     * 알람수정
     * @author:
     * @param:
     * @return:
     */

    /**
     * 알람 비활성화
     * @author: 최서현
     * @param: takeListId
     * @return:
     */
    @DeleteMapping("/alarms/{takeListId}")
    public void deactivateAlarm(@PathVariable("takeListId") Long takeListId){
        takeListService.updateIsAlarmFalse(takeListId);
    }

}
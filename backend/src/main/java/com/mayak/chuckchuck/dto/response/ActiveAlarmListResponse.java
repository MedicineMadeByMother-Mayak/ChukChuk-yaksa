package com.mayak.chuckchuck.dto.response;

import com.mayak.chuckchuck.domain.TakeList;
import com.mayak.chuckchuck.dto.Alarm;

import java.util.List;
import java.util.stream.Collectors;

//활성화된 알람리스트 조회
public record ActiveAlarmListResponse(List<Alarm> alarms) {
    public static ActiveAlarmListResponse fromEntity(List<TakeList> takeLists){
       List<Alarm> alarms = takeLists.stream()
               .map(takeList -> new Alarm(takeList.getTakeListId(), takeList.getTakeListName()))
               .collect(Collectors.toList());
       return new ActiveAlarmListResponse(alarms);
    }
}

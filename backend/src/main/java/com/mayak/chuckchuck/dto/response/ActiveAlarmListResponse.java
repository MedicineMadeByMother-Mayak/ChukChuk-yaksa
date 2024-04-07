package com.mayak.chuckchuck.dto.response;

import com.mayak.chuckchuck.domain.TakeList;
import com.mayak.chuckchuck.dto.AlarmDto;

import java.util.List;
import java.util.stream.Collectors;

//활성화된 알람리스트 조회
public record ActiveAlarmListResponse(List<AlarmDto> alarmDtos) {
    public static ActiveAlarmListResponse fromEntity(List<TakeList> takeLists){
       List<AlarmDto> alarmDtos = takeLists.stream()
               .map(takeList -> new AlarmDto(takeList.getTakeListId(), takeList.getTakeListName()))
               .collect(Collectors.toList());
       return new ActiveAlarmListResponse(alarmDtos);
    }
}

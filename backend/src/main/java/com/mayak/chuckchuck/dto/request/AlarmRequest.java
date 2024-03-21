package com.mayak.chuckchuck.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record AlarmRequest(
        Long takeListId, //복용해야하는 약 리스트 id
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        LocalDateTime alarmTime, // 알람 활성 시간
        @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
        int cycle // 알람 주기(필수X) defalut = 24
) {
}

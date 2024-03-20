package com.mayak.chuckchuck.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mayak.chuckchuck.enums.BloodType;
import com.mayak.chuckchuck.enums.Sex;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;


public record UserInfoRequest (
    @Size(max=20, message = "이름은 1에서 20자 사이여야 합니다.") String userName,
    Sex sex,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul") LocalDateTime birth,
    BloodType bloodType,
    int height,
    int weight
){
}
package com.mayak.chuckchuck.dto.request;

import com.mayak.chuckchuck.enums.BloodType;
import com.mayak.chuckchuck.enums.Sex;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@NoArgsConstructor
@ToString
public class UserInfoRequest {
//    private String social;
//    private Long socialId;
    private String userName;
    private Sex sex;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime birth;
    private BloodType bloodType;
    private int height;
    private int weight;
}
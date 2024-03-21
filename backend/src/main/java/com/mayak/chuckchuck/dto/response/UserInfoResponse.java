package com.mayak.chuckchuck.dto.response;

import com.mayak.chuckchuck.domain.User;
import com.mayak.chuckchuck.enums.BloodType;
import com.mayak.chuckchuck.enums.Sex;
import com.mayak.chuckchuck.enums.SocialCode;

import java.time.LocalDateTime;

public record UserInfoResponse(
        String social,
        SocialCode socialCode,
        String userName,
        Sex sex,
        LocalDateTime birth,
        BloodType bloodType,
        int height,
        int weight
) {

    public static UserInfoResponse fromEntity(User user) {
        return new UserInfoResponse(
                user.getSocial(),
                user.getSocialCode(),
                user.getUserName(),
                user.getSex(),
                user.getBirth(),
                user.getBloodType(),
                user.getHeight(),
                user.getWeight()
        );
    }

}

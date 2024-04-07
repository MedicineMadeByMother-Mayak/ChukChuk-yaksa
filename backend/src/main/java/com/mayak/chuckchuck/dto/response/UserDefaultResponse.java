package com.mayak.chuckchuck.dto.response;

import com.mayak.chuckchuck.domain.User;
import com.mayak.chuckchuck.enums.RoleType;
import com.mayak.chuckchuck.enums.SocialCode;

// 로그인, 회원정보 수정, 프로필 이미지 수정에서 사용할 Response
public record UserDefaultResponse(
        String email,
        SocialCode socialCode,
//        RoleType roleType,
        String userName
) {

    public static UserDefaultResponse fromUser(User dto) {
        return new UserDefaultResponse(
                dto.getUserName(),
                dto.getSocialCode(),
//                dto.roleType(),
                dto.getUserName()
        );
    }
}
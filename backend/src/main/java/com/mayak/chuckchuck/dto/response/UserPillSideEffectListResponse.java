package com.mayak.chuckchuck.dto.response;

import com.mayak.chuckchuck.domain.Pill;
import com.mayak.chuckchuck.domain.UserPillEffect;
import com.mayak.chuckchuck.dto.SideEffectPill;

import java.util.ArrayList;
import java.util.List;

public record UserPillSideEffectListResponse(List<SideEffectPill> userPillSIdeEffectList) {
    public static UserPillSideEffectListResponse fromEntity(List<UserPillEffect> userPillEffectList) {
        List<SideEffectPill> userPillEffectListResult = new ArrayList<>();

        userPillEffectListResult = userPillEffectList.stream()
                .map(temp -> {
                    Pill tempPill = temp.getPill();
                    return new SideEffectPill(tempPill.getCompany(), tempPill.getName(), temp.getMemo());
                }).toList();

        return new UserPillSideEffectListResponse(userPillEffectListResult);
    }
}

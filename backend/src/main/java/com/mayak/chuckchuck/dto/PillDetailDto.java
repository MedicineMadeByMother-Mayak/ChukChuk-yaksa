package com.mayak.chuckchuck.dto;

import com.mayak.chuckchuck.domain.Pill;
import com.mayak.chuckchuck.domain.UserPillEffect;

import java.util.List;

public record PillDetailDto(
        Long pill_id,
        String name,
        String company,
        String effect,
        String basis,
        String caution,
        String capacity,
        String image_url,
        String type,
        boolean warning_pregnant,
        boolean warning_use_date,
        boolean warning_elders,
        boolean warning_together
) {
    public static PillDetailDto fromEntity(UserPillEffect userPillEffect) {
        Pill pill = userPillEffect.getPill();
        return new PillDetailDto(
                pill.getPillId(),
                pill.getName(),
                pill.getCompany(),
                pill.getEffect(),
                pill.getBasis(),
                pill.getCaution(),
                pill.getCapacity(),
                pill.getImageUrl(),
                pill.getType(),
                pill.getWarningPregnant(),
                pill.getWarningUseDate(),
                pill.getWarningElders(),
                pill.getWarningTogether()
        );
    }

    public static List<PillDetailDto> fromEntity(List<Pill> pillList) {
        return pillList.stream()
                .map(pill -> new PillDetailDto(
                        pill.getPillId(),
                        pill.getName(),
                        pill.getCompany(),
                        pill.getEffect(),
                        pill.getBasis(),
                        pill.getCaution(),
                        pill.getCapacity(),
                        pill.getImageUrl(),
                        pill.getType(),
                        pill.getWarningPregnant(),
                        pill.getWarningUseDate(),
                        pill.getWarningElders(),
                        pill.getWarningTogether()
                ))
                .toList(); // toList()를 추가하여 Stream을 List로 변환합니다.
    }
}

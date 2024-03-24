package com.mayak.chuckchuck.dto.response;

import com.mayak.chuckchuck.domain.Pill;
import com.mayak.chuckchuck.dto.PillDetailDto;

public record PillDetailInfoResultResponse(PillDetailDto pillDetailDto) {
    public static PillDetailInfoResultResponse fromEntity(Pill pill) {
        PillDetailDto pillDetailDto = new PillDetailDto(
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
                pill.getWarningTogether());

        return new PillDetailInfoResultResponse(pillDetailDto);
    }
}

package com.mayak.chuckchuck.dto.response;

import com.mayak.chuckchuck.domain.Pill;
import com.mayak.chuckchuck.dto.PillDetailDto;
import com.mayak.chuckchuck.dto.TagDto;

import java.util.List;

public record PillDetailInfoResultResponse(PillDetailDto pillDetailDto) {
    public static PillDetailInfoResultResponse fromEntity(Pill pill, List<TagDto> usedTags) {
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
                pill.getWarningTogether(),
                usedTags);

        return new PillDetailInfoResultResponse(pillDetailDto);
    }
}

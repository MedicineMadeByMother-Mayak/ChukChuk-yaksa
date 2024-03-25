package com.mayak.chuckchuck.dto.response;

import com.mayak.chuckchuck.dto.PillDetailDto;

import java.util.List;

public record UserPillEffectListAndSearchResponse(
        List<PillDetailDto> totalPillDtoList,
        List<PillDetailDto> siedEffectPillDtoList,
        List<PillDetailDto> stopPillDtoList,
        List<PillDetailDto> effectPillDtoList) {

}

package com.mayak.chuckchuck.dto.response;

import com.mayak.chuckchuck.dto.PillDetailDto;

import java.util.List;

public record UserPillEffectListAndSearchResponse(
        int totalPillDtoListCount,
        List<PillDetailDto> totalPillDtoList,
        int siedEffectPillDtoListCount,
        List<PillDetailDto> siedEffectPillDtoList,
        int stopPillDtoListCount,
        List<PillDetailDto> stopPillDtoList,
        int effectPillDtoListCount,
        List<PillDetailDto> effectPillDtoList) {
}

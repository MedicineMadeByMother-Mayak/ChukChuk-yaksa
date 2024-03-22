package com.mayak.chuckchuck.dto.response;

import com.mayak.chuckchuck.domain.Pill;
import com.mayak.chuckchuck.dto.PillSummaryDto;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public record PillSearchResultResponse(
        Long count,
        List<PillSummaryDto> pills
) {
    public static PillSearchResultResponse fromEntity(Page<Pill> latestResult, Long totalCount) {
        return new PillSearchResultResponse(totalCount,
                latestResult.stream().map(pill -> new PillSummaryDto(pill.getPillId(), pill.getName(),
                                pill.getImageUrl(), pill.getType(), pill.getWarningPregnant(),
                                pill.getWarningUseDate(), pill.getWarningElders(), pill.getWarningTogether()))
                        .collect(Collectors.toList()));
    }
}

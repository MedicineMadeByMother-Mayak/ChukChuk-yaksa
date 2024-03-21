package com.mayak.chuckchuck.dto.response;

import com.mayak.chuckchuck.domain.Pill;
import com.mayak.chuckchuck.dto.PillSummaryDto;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public record PillSearchResultResp(
        Long count,
        List<PillSummaryDto> pills
) {
    public static PillSearchResultResp fromEntity(Page<Pill> latestResult, Long totalCount) {
        return new PillSearchResultResp(totalCount,
                latestResult.stream().map(pill -> new PillSummaryDto(pill.getPillId(), pill.getName(),
                                pill.getImageUrl(), pill.getType(), pill.getWarningPregnant(),
                                pill.getWarningUseDate(), pill.getWarningElders(), pill.getWarningTogether()))
                        .collect(Collectors.toList()));
    }
}

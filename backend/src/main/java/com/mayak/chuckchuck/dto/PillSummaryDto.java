package com.mayak.chuckchuck.dto;

public record PillSummaryDto(
        Long pillId,
        String pillName,
        String imageUrl,
        String type,
        Boolean warningPregnant,
        Boolean warningUseDate,
        Boolean warningElders,
        Boolean warningTogether
) {
}

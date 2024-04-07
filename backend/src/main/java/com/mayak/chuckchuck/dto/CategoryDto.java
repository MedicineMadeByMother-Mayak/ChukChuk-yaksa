package com.mayak.chuckchuck.dto;

import java.util.List;

public record CategoryDto(
        Long userPillEffectId,
        Long categoryId,
        String categoryName,
        List<TagDto> usedTags,
        String memo
) {
}

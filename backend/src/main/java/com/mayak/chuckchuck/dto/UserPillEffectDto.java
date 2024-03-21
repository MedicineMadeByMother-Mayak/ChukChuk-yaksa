package com.mayak.chuckchuck.dto;

import java.util.List;

public record UserPillEffectDto(
        Long userPillEffectId,
        Long categoryId,
        String categoryName,
        List<TagDto> usedTags,
        List<TagDto> unUsedTags,
        String memo
) {
    public record Tag (Long tagId, String tagName) {}
}

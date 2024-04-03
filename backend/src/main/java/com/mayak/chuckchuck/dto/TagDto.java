package com.mayak.chuckchuck.dto;

import com.mayak.chuckchuck.domain.Tag;
import com.mayak.chuckchuck.domain.UserPillEffectToTag;

import java.util.List;
import java.util.stream.Collectors;

public record TagDto(
        Long categoryId,
        Long tagId,
        String tagName
) {
    public static List<TagDto> fromEntity(List<UserPillEffectToTag> userPillEffectToTags) {
        return userPillEffectToTags.stream()
                .map(userPillEffectToTag -> {
                    Tag tempTag = userPillEffectToTag.getTag();

                    return new TagDto(tempTag.getTagId(), tempTag.getTagId(), tempTag.getTagName());
                })
                .collect(Collectors.toList());
    }
}

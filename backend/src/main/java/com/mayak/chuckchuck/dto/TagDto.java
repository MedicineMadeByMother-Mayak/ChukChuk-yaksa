package com.mayak.chuckchuck.dto;

public record TagDto(
        Long categoryId,
        Long tagId,
        String tagName
) {
}

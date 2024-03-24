package com.mayak.chuckchuck.dto.request;

public record UserPillEffectListAndSearchRequest(
        Long categoryId,
        String keyword,
        String page
) {
}

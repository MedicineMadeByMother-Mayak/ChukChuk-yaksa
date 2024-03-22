package com.mayak.chuckchuck.dto.request;

import com.mayak.chuckchuck.dto.CategoryDto;

import java.util.List;

public record UserPillEffectRegistInfoRequest(
        Long pillId,
        List<CategoryDto> category
) {
}


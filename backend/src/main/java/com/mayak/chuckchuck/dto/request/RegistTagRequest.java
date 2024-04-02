package com.mayak.chuckchuck.dto.request;

import jakarta.validation.constraints.NotNull;

public record RegistTagRequest(
        @NotNull
        String tagName,
        @NotNull
        Long categoryId
) {
}

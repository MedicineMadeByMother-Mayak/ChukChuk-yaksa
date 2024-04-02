package com.mayak.chuckchuck.dto.request;

import jakarta.validation.constraints.NotNull;

public record UseRegistedTagRequest(
        @NotNull
        Long tagId,
        @NotNull
        Long pillId
) {
}
package com.mayak.chuckchuck.dto.request;

public record RegistTagRequest(
        String tagName,
        Long categoryId
) {
}

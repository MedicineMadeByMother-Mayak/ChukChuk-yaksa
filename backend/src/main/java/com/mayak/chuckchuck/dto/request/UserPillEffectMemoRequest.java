package com.mayak.chuckchuck.dto.request;

import jakarta.validation.constraints.Size;

public record UserPillEffectMemoRequest(
        Long usePillEffectId,
        @Size(max=100, message = "메모는 100자까지만 가능합니다.") String memo
) {

}
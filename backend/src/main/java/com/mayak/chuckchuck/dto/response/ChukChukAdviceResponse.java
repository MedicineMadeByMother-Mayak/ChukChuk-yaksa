package com.mayak.chuckchuck.dto.response;

import com.mayak.chuckchuck.dto.ChukchukAdviceDto;

import java.util.List;

public record ChukChukAdviceResponse(List<ChukchukAdviceDto> chukchukAdviceDtos) {
    public static ChukChukAdviceResponse fromEntity(List<ChukchukAdviceDto> chukchukAdviceDtos) {
        return new ChukChukAdviceResponse(chukchukAdviceDtos);
    }
}
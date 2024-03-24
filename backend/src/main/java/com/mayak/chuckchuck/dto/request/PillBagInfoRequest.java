package com.mayak.chuckchuck.dto.request;

import com.mayak.chuckchuck.dto.PrescriptionInfoDto;

import java.time.LocalDateTime;
import java.util.List;

public record PillBagInfoRequest(
        LocalDateTime buildDate,
        String pharmacyName,
        int cost,
        List<PrescriptionInfoDto> pills
) {
}

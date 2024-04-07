package com.mayak.chuckchuck.dto;

import java.math.BigDecimal;

public record PharmacyDto(
        String title,
        BigDecimal lat,
        BigDecimal lng,
        String address
) {
}
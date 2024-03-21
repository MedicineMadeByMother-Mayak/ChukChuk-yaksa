package com.mayak.chuckchuck.dto.response;

import com.mayak.chuckchuck.dto.PharmacyDto;

public record PharmacyResponse(
        PharmacyDto[] pharmacies
) {
    public static PharmacyResponse fromArr(PharmacyDto[] pharmacies) {
        return new PharmacyResponse(
                pharmacies
        );
    };
}

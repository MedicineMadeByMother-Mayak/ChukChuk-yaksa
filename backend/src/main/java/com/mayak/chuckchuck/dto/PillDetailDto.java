package com.mayak.chuckchuck.dto;

public record PillDetailDto(
        Long pill_id,
        String name,
        String company,
        String effect,
        String basis,
        String caution,
        String capacity,
        String image_url,
        String type,
        boolean warning_pregnant,
        boolean warning_use_date,
        boolean warning_elders,
        boolean warning_together
) {
}

package com.mayak.chuckchuck.dto;

import com.mayak.chuckchuck.domain.Pill;

//복약안내(사진, 약종류, 약이름, 약ID, 안내)
public record PrescriptionInfoDto(
        Long pillId,
        String pillName,
        String imageUrl,
        String type,
        String guide
) {
    public static PrescriptionInfoDto fromEntityAndGuide(Pill pill, String guide){
        return new PrescriptionInfoDto(pill.getPillId(), pill.getName(),
                pill.getImageUrl(), pill.getType(), guide);
    }
}

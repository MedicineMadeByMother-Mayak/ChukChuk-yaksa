package com.mayak.chuckchuck.dto.response;

import com.mayak.chuckchuck.dto.PrescriptionInfoDto;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

public record PillBagOCRInfoResponse(
        LocalDateTime buildDate,
        String pharmacyName,
        int cost,
        List<PrescriptionInfoDto> pills
) {
    public static PillBagOCRInfoResponse fromValidOcrResult(HashMap<String, Object> ocrResult){
        LocalDateTime buildDate = LocalDateTime.parse((String) ocrResult.get("buildDate") + "T00:00:00");

        int cost = Integer.parseInt(((String) ocrResult.get("cost")).replaceAll(",", ""));

        String pharmacyName = (String) ocrResult.get("pharmacyName");
        List<PrescriptionInfoDto> prescriptionInfoList = (List<PrescriptionInfoDto>) ocrResult.get("prescriptionInfo");

        return new PillBagOCRInfoResponse(
                buildDate,
                pharmacyName,
                cost,
                prescriptionInfoList
        );
    }
}

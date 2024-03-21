package com.mayak.chuckchuck.dto.response;

import com.mayak.chuckchuck.domain.Diagnosis;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public record DiseaseResponse(List<DiseaseDto> results) {
    public static DiseaseResponse fromEntity(List<Diagnosis> diagnosisList) {
        List<DiseaseDto> diseases = diagnosisList.stream()
                .map(diagnosis -> new DiseaseDto(
                        diagnosis.getDiagnosisDate(),
                        diagnosis.getIllCode(),
                        diagnosis.getIllName()))
                .sorted(Comparator.comparing(DiseaseDto::Date).reversed()) // 내림차순 정렬
                .collect(Collectors.toList());

        return new DiseaseResponse(diseases);
    }

    public record DiseaseDto(LocalDateTime Date, String Code, String Name) {
    }
}

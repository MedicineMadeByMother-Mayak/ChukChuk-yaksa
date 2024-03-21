package com.mayak.chuckchuck.dto.response;

import com.mayak.chuckchuck.domain.Diagnosis;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public record DiseaseResponse(List<Disease> results) {
    public static DiseaseResponse fromEntity(List<Diagnosis> diagnosisList) {
        List<Disease> diseases = diagnosisList.stream()
                .map(diagnosis -> new Disease(
                        diagnosis.getDiagnosisDate(),
                        diagnosis.getIllCode(),
                        diagnosis.getIllName()))
                .sorted(Comparator.comparing(Disease::Date).reversed()) // 내림차순 정렬
                .collect(Collectors.toList());

        return new DiseaseResponse(diseases);
    }

    public record Disease ( LocalDateTime Date, String Code, String Name) {
    }
}

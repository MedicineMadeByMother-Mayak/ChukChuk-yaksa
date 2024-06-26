package com.mayak.chuckchuck.dto.response;

import com.mayak.chuckchuck.domain.Diagnosis;
import com.mayak.chuckchuck.dto.DiagnosisDto;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public record DiagnosisResponse(int count, List<DiagnosisDto> result) {

    public static DiagnosisResponse fromEntity(Page<Diagnosis> diagnosises) {
        List<DiagnosisDto> result = diagnosises.stream()
                .map(diagnosisDtos -> new DiagnosisDto(
                        diagnosisDtos.getDiagnosisDate(),
                        diagnosisDtos.getHospitalName(),
                        diagnosisDtos.getIllCode(),
                        diagnosisDtos.getIllName(),
                        diagnosisDtos.getOpinion()))
                .collect(Collectors.toList());

        return new DiagnosisResponse((int) diagnosises.getTotalElements(), result);
    }
}

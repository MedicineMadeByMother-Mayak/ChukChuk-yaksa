package com.mayak.chuckchuck.dto;

import java.time.LocalDateTime;

public record DiagnosisDto(LocalDateTime diagnosisDate,
                           String hospitalName,
                           String illCode,
                           String illName,
                           String opinion) {

}
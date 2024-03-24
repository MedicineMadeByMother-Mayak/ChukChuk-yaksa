package com.mayak.chuckchuck.dto.request;

import java.time.LocalDateTime;

public record DiagnosisInfoResquest(LocalDateTime diagnosisDate,
                                    String hospitalName,
                                    String illCode,
                                    String illName,
                                    String opinion) {
}

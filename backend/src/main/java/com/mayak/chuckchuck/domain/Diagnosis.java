package com.mayak.chuckchuck.domain;

import com.mayak.chuckchuck.dto.request.DiagnosisInfoResquest;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@DiscriminatorValue("DIAGNOSIS")
@NoArgsConstructor
//진단서
public class Diagnosis extends OCRList{

    //질병 code
    @Column(name = "ill_code", length = 50)
    private String illCode;

    //질병 name
    @Column(name = "ill_name", length = 50)
    private String illName;

    //병원명
    @Column(name = "hospital_name", length = 50)
    private String hospitalName;

    //진단일
    @Column(name = "diagnosis_date")
    private LocalDateTime diagnosisDate;

    //소견
    @Column(length = 1000)
    private String opinion;

    //공통 데이터
    @Embedded
    private CommonData commonData = new CommonData();


    private Diagnosis(User user, String illCode, String illName, String hospitalName, LocalDateTime diagnosisDate, String opinion) {
        this.illCode = illCode;
        this.illName = illName;
        this.hospitalName = hospitalName;
        this.diagnosisDate = diagnosisDate;
        this.opinion = opinion;
        this.user = user;
    }

    public static Diagnosis createDiagnosis(User user, DiagnosisInfoResquest dianosisInfo) {
        return new Diagnosis(
                user,
                dianosisInfo.illCode(),
                dianosisInfo.illName(),
                dianosisInfo.hospitalName(),
                dianosisInfo.diagnosisDate(),
                dianosisInfo.opinion()
        );
    }
}

package com.mayak.chuckchuck.domain;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("DIAGNOSIS")
//진단서
public class Diagnosis extends OCRList{

    //질병 code
    @Column(name = "ill_code")
    private String illCode;

    //질병 name
    @Column(name = "ill_name")
    private String illName;

    //병원명
    @Column(name = "hospital_name")
    private String hospitalName;

    //진단일
    @Column(name = "diagnosis_date")
    private LocalDateTime diagnosisDate;

    //소견
    @Column
    private String opinion;
}

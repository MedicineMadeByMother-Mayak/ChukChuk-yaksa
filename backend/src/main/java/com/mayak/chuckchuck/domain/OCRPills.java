package com.mayak.chuckchuck.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Getter
@ToString
@Table(name="ocr_pills")
//약봉투에 있는 약
public class OCRPills{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="OCR_pills_id")
    private Long OCRPillsId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="OCR_id")
    private OCRList ocrList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="pill_id")
    private Pill pill;

    //복약 안내
    private String guide;

    //공통 데이터
    @Embedded
    private CommonData commonData;
}
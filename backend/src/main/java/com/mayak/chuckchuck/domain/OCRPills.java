package com.mayak.chuckchuck.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

@Entity
@Getter
@ToString
@Table(name="ocr_pills")
//약봉투에 있는 약
public class OCRPills{

    //약봉투에 있는 약 id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="OCR_pills_id")
    private Long OCRPillsId;

    //약봉투/진단서 id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="OCR_id")
    private OCRList ocrList;

    //약 id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="pill_id")
    private Pill pill;

    //복약 안내
    @Column(length = 100)
    private String guide;

    //공통데이터
    @Embedded
    private CommonData commonData = new CommonData();

    private OCRPills(OCRList ocrList, Pill pill, String guide){
        this.ocrList = ocrList;
        this.pill = pill;
        this.guide = guide;
    }

    public static OCRPills createOCRPills(Pill pill, PillBag pillBag, String guide) {
        return new OCRPills(pillBag, pill, guide);
    }
}
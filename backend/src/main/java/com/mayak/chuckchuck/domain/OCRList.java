package com.mayak.chuckchuck.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@DiscriminatorColumn(name = "ocr_type")
@Table(name = "ocr_list")
//약봉투,진단서 리스트
public class OCRList {

    //약봉투/진단서 id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ocr_id")
    private Long ocrId;

    //사용자 id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    //공통 데이터
    @Embedded
    private CommonData commonData;

}
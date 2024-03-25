package com.mayak.chuckchuck.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "pill")
@ToString
//약
public class Pill {

    //약 id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="pill_id")
    private Long pillId;

    //약 이름
    @Column(name="name", length = 50)
    private String name;

    //약 코드
    private Long code;

    //약 제조사
    @Column(name="company", length = 50)
    private String company;

    //약 효과
    @Column(length = 2000)
    private String effect;

    //약 주성분
    @Column(length = 2000)
    private String basis;

    //약 주의사항
    @Column(length = 2000)
    private String caution;

    //용법용량
    @Column(length = 2000)
    private String capacity;

    //약 이미지 링크
    @Column(name="image_url", length = 1000)
    private String imageUrl;

    //약 분류
    @Column(length = 100)
    private String type;

    //임산부 주의
    @Column(name="warning_pregnant")
    private Boolean warningPregnant;

    //투여기간주의
    @Column(name="warning_use_date")
    private Boolean warningUseDate;

    //노인주의
    @Column(name="warning_elders")
    private Boolean warningElders;

    //병용금기
    @Column(name="warning_together")
    private Boolean warningTogether;

    @Embedded
    private CommonData commonData = new CommonData();;
}

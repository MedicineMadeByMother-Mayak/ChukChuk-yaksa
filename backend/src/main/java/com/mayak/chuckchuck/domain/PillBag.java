package com.mayak.chuckchuck.domain;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Getter
@Entity
@DiscriminatorValue("PILLBAG")
//약봉투
public class PillBag extends OCRList{

    //조제일자
    @Column(name = "build_date")
    private LocalDateTime buildDate;

    //약국명
    @Column(name = "pharmacy_name", length = 100)
    private String pharmacyName;

    //수납금액
    @Column
    @ColumnDefault(value = "0")
    private Integer cost;
}

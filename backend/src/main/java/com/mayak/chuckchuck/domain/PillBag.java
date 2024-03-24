package com.mayak.chuckchuck.domain;

import com.mayak.chuckchuck.dto.request.PillBagInfoRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Getter
@Entity
@DiscriminatorValue("PILLBAG")
@NoArgsConstructor
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

    //공통 데이터
    @Embedded
    private CommonData commonData = new CommonData();

    private PillBag(LocalDateTime buildDate, String pharmacyName, int cost,
                    User user){
        this.buildDate = buildDate;
        this.pharmacyName = pharmacyName;
        this.cost = cost;
        this.user = user;
    }

    public static PillBag createPillBag(PillBagInfoRequest pillBagInfo, User user){
        return new PillBag(pillBagInfo.buildDate(), pillBagInfo.pharmacyName(),
                pillBagInfo.cost(), user);
    }
}

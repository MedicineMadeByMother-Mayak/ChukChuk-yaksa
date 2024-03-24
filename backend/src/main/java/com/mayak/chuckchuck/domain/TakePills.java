package com.mayak.chuckchuck.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name="take_pills")
//리스트에 포함된 약(중간테이블)
public class TakePills {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="take_pills_id")
    private Long takePillsId;

    //약
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="pill_id")
    private Pill pill;

    //복용리스트
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="take_list_id")
    private TakeList takeList;

    //공통데이터
    @Embedded
    private CommonData commonData;

    //복용 리스트 약 추가에 사용
    private TakePills(TakeList takeList, Pill pill){
        this.takeList = takeList;
        this.pill = pill;
        this.commonData =new CommonData(); // isDelete, createDate 자동생성

    }
    //복용 리스트 약 삭제에 사용
    public void deletePill() {
        this.commonData.toggleDelete();
    }

    public static TakePills createTakePills(TakeList takeList, Pill pill){
        return new TakePills(takeList, pill);
    }

}

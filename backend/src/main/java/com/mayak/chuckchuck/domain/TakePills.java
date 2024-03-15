package com.mayak.chuckchuck.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
}

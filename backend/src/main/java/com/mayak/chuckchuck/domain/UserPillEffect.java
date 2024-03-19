package com.mayak.chuckchuck.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
@Table(name="user_pill_effect")
//사용자-약-약효 대분류
public class UserPillEffect {
    //사용자-약효 대분류 id(약효 기록 id)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_pill_effect_id")
    private Long userPillEffectId;

    //카테고리(대분류)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_id")
    private Category category;

    //사용자
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;
    
    //약
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="pill_id")
    private Pill pill;

    //메모
    @Column(length = 100)
    private String memo;
    
    //공통 데이터
    @Embedded
    private CommonData commonData;
}
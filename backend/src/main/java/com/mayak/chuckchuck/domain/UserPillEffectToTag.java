package com.mayak.chuckchuck.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "user_pill_effect_to_tag")
public class UserPillEffectToTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_pill_effect_to_tag_id")
    private Long userPillEffectToTagId;

    //사용자약 약효 대분류
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_pill_effect_id")
    private UserPillEffect userPillEffect;

    //태그
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="tag_id")
    private Tag tag;

    //공통 데이터
    @Embedded
    private CommonData commonData;
}
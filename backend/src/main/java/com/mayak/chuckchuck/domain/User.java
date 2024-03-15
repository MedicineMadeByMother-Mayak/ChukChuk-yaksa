package com.mayak.chuckchuck.domain;


import com.mayak.chuckchuck.enums.BloodType;
import com.mayak.chuckchuck.enums.Sex;
import com.mayak.chuckchuck.enums.SocialCode;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "user")
//사용자
public class User {

    //사용자id
    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    //소셜로그인 플랫폼 code
    @Enumerated(EnumType.STRING)
    @Column(name="social_code")
    private SocialCode socialCode;

    //소셜로그인 플랫폼 이름
    private String social;

    //사용자이름
    @Column(name="user_name")
    private String userName;

    //생일
    private LocalDateTime birth;

    //혈액형
    @Enumerated(EnumType.STRING)
    @Column(name="blood_type")
    private BloodType bloodType;

    //키
    private int height;

    //몸무게
    private int weight;

    //성별
    @Enumerated(EnumType.STRING)
    private Sex sex;

    //토큰
    private String token;

    //공통데이터
    @Embedded
    private CommonData commonData;


}

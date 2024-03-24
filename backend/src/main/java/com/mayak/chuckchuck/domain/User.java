package com.mayak.chuckchuck.domain;


import com.mayak.chuckchuck.dto.request.UserInfoRequest;
import com.mayak.chuckchuck.enums.BloodType;
import com.mayak.chuckchuck.enums.Sex;
import com.mayak.chuckchuck.enums.SocialCode;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
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
    @Column(length = 100)
    private String social;

    //사용자이름
    @Column(name="user_name", length = 20)
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
    @Column(length = 100)
    private String token;

    //공통데이터
    @Embedded
    private CommonData commonData = new CommonData();;

    private User(
        SocialCode socialCode,
        String social,
        String userName,
        LocalDateTime birth,
        BloodType bloodType,
        int height,
        int weight,
        Sex sex,
        String token,
        CommonData commonData
    ) {
        this.socialCode = socialCode;
        this.social = social;
        this.userName = userName;
        this.birth = birth;
        this.bloodType = bloodType;
        this.height = height;
        this.weight = weight;
        this.sex = sex;
        this.token = token;
        this.commonData = commonData;
    }

    /**
     * User의 기타정보를 변경
     * @author: 최서현
     * @param: userInfoRequest
     */
    public void updateInfo(UserInfoRequest userInfoRequest) {
        this.userName = userInfoRequest.userName();
        this.sex = userInfoRequest.sex();
        this.birth = userInfoRequest.birth();
        this.bloodType = userInfoRequest.bloodType();
        this.height = userInfoRequest.height();
        this.weight = userInfoRequest.weight();
    }

    public static User of(SocialCode socialCode, String social) {
        return of(socialCode, social, null, null, null, 0, 0, null, null);
    }

    public static User of(SocialCode socialCode, String social, String userName, LocalDateTime birth, BloodType bloodType, int height, int weight, Sex sex, CommonData commonData) {
        return of(socialCode, social, userName, birth, bloodType, height, weight, sex, null, commonData);
    }

    public static User of(SocialCode socialCode, String social, String userName, LocalDateTime birth, BloodType bloodType, int height, int weight, Sex sex, String token, CommonData commonData) {
        return new User(socialCode, social, userName, birth, bloodType, height, weight, sex, token, commonData);
    }
}

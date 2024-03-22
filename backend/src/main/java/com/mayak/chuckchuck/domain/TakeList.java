package com.mayak.chuckchuck.domain;

import com.mayak.chuckchuck.dto.request.UpdateTakeListRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "take_list")
//복용리스트
public class TakeList {
    //복용리스트 id
    @Id
    @Column(name="take_list_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long takeListId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    //복용리스트명
    @Column(name="take_list_name", length = 10)
    private String takeListName;

    //복용알림여부
    @Column(name="is_alarm")
    private Boolean isAlarm;

    //알림시간
    @Column(name="alarm_time")
    private LocalDateTime alarmTime;

    //반복주기
    @ColumnDefault(value = "24")
    private int cycle;

    //복용 완료 여부
    @Column(name="is_finish")
    private Boolean isFinish;

    //복용완료날짜
    @Column(name="finish_date")
    private LocalDateTime finishDate;

    //공통 데이터
    @Embedded
    private CommonData commonData;


    public void toggleAlarm(){
        this.isAlarm = !this.isAlarm;
    }

    public void updateAlarm(LocalDateTime alarmTime, int cycle) {
        this.isAlarm = true;
        this.alarmTime = alarmTime;
        this.cycle = cycle;
    }

    public void updateTakeListName(UpdateTakeListRequest updateTakeListRequest){
        this.takeListName = updateTakeListRequest.updateTakeName();
    }

    // 복용리스트 완료
    public void finishTakeList(){
        this.isFinish = !this.isFinish;
        this.finishDate = LocalDateTime.now();
    }

    // 복용리스트 삭제
    public void deleteTakeList(){
        this.commonData.toggleDelete();
    }
}



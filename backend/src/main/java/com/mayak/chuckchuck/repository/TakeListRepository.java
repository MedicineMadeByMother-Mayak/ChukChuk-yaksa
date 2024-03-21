package com.mayak.chuckchuck.repository;

import com.mayak.chuckchuck.domain.TakeList;
import com.mayak.chuckchuck.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface TakeListRepository extends JpaRepository<TakeList, Long> {

    /**
     * 로그인유저의 알림을 활성화한 복용리스트
     * @author: 최서현
     * @param: User
     * @return: TakeList
     */
    List<TakeList> findByUserAndIsAlarmTrue(User user);

    /**
     * 복용리스트 조회
     * @author: 김보경
     * @param: baseDate, isFinish
     * @return: TakeList
     */
    List<TakeList> findByUserAndFinishDateGreaterThanEqualOrIsFinish(User user, LocalDateTime baseDate, boolean isFinish);
}

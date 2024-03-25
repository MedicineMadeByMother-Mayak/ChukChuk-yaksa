package com.mayak.chuckchuck.repository;

import com.mayak.chuckchuck.domain.TakeList;
import com.mayak.chuckchuck.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
    @Query("SELECT t FROM TakeList t WHERE t.user = :user AND (t.finishDate >= :baseDate OR t.isFinish = false) AND t.commonData.isDelete = false ORDER BY t.commonData.createDate DESC")
    List<TakeList> findTakeListByUserIdAndFinishDateAndIsFinish(@Param("user") User user, @Param("baseDate") LocalDateTime baseDate);

    /**
     * 복용리스트 약 추가
     * @author: 김보경
     */
    @Query("SELECT t FROM TakeList t WHERE t.user = :user AND t.takeListName = :takeListName AND t.commonData.isDelete = false")
    Optional<TakeList> findByUserAndTakeListName(@Param("user") User user, @Param("takeListName") String takeListName);

    default Optional<TakeList> findByUserAndDateString(User user, String dateString) {
        String takeListName = dateString + "에 추가한 약";
        return findByUserAndTakeListName(user, takeListName);
    }
}

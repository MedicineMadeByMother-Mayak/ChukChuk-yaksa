package com.mayak.chuckchuck.repository;

import com.mayak.chuckchuck.domain.TakeList;
import com.mayak.chuckchuck.domain.TakePills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TakePillsRepository extends JpaRepository<TakePills, Long> {

    /**
     * 복용리스트 조회
     * @author: 김보경
     * @param: TakeListId
     * @return: TakePills
     */
    List<TakePills> findByTakeList(TakeList takeList);

    /**
     * 복용리스트 약 추가
     * 중복되는 약은 추가하지 않기 위해
     * takeList 에 존재하는 List<pillId> 찾는 쿼리문
     * @author: 김보경
     * @param: TakeListId
     * @return: List<pillId>
     */
    @Query("SELECT tp.pill.pillId FROM TakePills tp WHERE tp.takeList.takeListId = :takeListId")
    List<Long> findPillIdsByTakeListId(@Param("takeListId") Long takeListId);
}
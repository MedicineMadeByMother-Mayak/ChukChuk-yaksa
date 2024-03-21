package com.mayak.chuckchuck.repository;

import com.mayak.chuckchuck.domain.TakeList;
import com.mayak.chuckchuck.domain.TakePills;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TakePillsRepository extends JpaRepository<TakePills, Long> {

    /**
     * 복용리스트 조회
     * @author: 김보경
     * @param: TakeListId
     * @return: TakePills
     */
    List<TakePills> findByTakeList(TakeList takeList);
}
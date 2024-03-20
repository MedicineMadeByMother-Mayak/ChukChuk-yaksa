package com.mayak.chuckchuck.service;

import com.mayak.chuckchuck.domain.TakeList;
import com.mayak.chuckchuck.repository.TakeListRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class TakeListService {
    private final TakeListRepository takeListRepository;

    /**
     * 알람 비활성화
     * @author: 최서현
     * @param: takeListId
     * @return:
     */
    public void updateIsAlarmFalse(Long takeListId) {
        TakeList takeList = takeListRepository.findById(takeListId).get();
        takeList.toggleAlarm();
    }

}

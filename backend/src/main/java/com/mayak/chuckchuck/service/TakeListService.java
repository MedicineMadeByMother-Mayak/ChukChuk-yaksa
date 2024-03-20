package com.mayak.chuckchuck.service;

import com.mayak.chuckchuck.domain.TakeList;
import com.mayak.chuckchuck.domain.User;
import com.mayak.chuckchuck.dto.response.ActiveAlarmListResponse;
import com.mayak.chuckchuck.repository.TakeListRepository;
import com.mayak.chuckchuck.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TakeListService {
    private final TakeListRepository takeListRepository;
    private final UserRepository userRepository;

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

    /**
     * 사용자의 모든 활성화 알람 리스트 조회
     *
     * @author: 최서현
     * @param:
     * @return: ActiveAlarmListResponse
     */
    public ActiveAlarmListResponse getAlarmList() {
        //===임시 User 객체 사용
        User user = userRepository.findById(1L).get();
        //===

        List<TakeList> takeLists = takeListRepository.findByUserAndIsAlarmTrue(user);
        return ActiveAlarmListResponse.fromEntity(takeLists);
    }
}

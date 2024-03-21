package com.mayak.chuckchuck.service;

import com.mayak.chuckchuck.domain.TakeList;
import com.mayak.chuckchuck.domain.User;
import com.mayak.chuckchuck.dto.response.ActiveAlarmListResponse;
import com.mayak.chuckchuck.exception.ErrorCode.CommonErrorCode;
import com.mayak.chuckchuck.exception.RestApiException;
import com.mayak.chuckchuck.repository.TakeListRepository;
import com.mayak.chuckchuck.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class TakeListService {
    private final TakeListRepository takeListRepository;
    private final UserRepository userRepository;

    /**
     * 복용리스트의 알람 비활성화
     * @author: 최서현
     * @param: takeListId
     */
    public void updateIsAlarmFalse(Long takeListId) {
        Optional<TakeList> takeListOptional = takeListRepository.findById(takeListId);
        if (takeListOptional.isPresent()) {
            TakeList takeList = takeListRepository.findById(takeListId).get();
            takeList.toggleAlarm();
        } else {
            throw new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND);
        }
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

    /**
     * 알람등록 및 수정
     * @author: 차현철
     * @param: Long takeListId, String alarmTime, String cycle
     * @return: ResponseEntity.ok()
     */
    public void updateAlarm(Long takeListId, LocalDateTime alarmTime, int cycle) {
        if (cycle == 0) cycle = 24;
        TakeList takeList = getTakeListOrException(takeListId);
        takeList.updateAlarm(alarmTime, cycle);
    }

    // 복용리스트 있으면 가져오고, 없으면 에러 반환
    private TakeList getTakeListOrException(Long takeListId) {
        return takeListRepository.findById(takeListId).orElseThrow(() ->
                new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND));
    }

    // 유저 있으면 가져오고, 없으면 에러 반환
    private User getUserOrException(Long userId) {
        return userRepository.findById(userId).orElseThrow(() ->
                new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND));
    }
}

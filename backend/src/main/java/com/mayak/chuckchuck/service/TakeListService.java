package com.mayak.chuckchuck.service;

import com.mayak.chuckchuck.domain.Pill;
import com.mayak.chuckchuck.domain.TakeList;
import com.mayak.chuckchuck.domain.TakePills;
import com.mayak.chuckchuck.domain.User;
import com.mayak.chuckchuck.dto.request.TakeListRequest;
import com.mayak.chuckchuck.dto.response.ActiveAlarmListResponse;
import com.mayak.chuckchuck.dto.response.TakeListResponse;
import com.mayak.chuckchuck.exception.ErrorCode.CommonErrorCode;
import com.mayak.chuckchuck.exception.RestApiException;
import com.mayak.chuckchuck.repository.TakeListRepository;
import com.mayak.chuckchuck.repository.TakePillsRepository;
import com.mayak.chuckchuck.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class TakeListService {
    private final TakeListRepository takeListRepository;
    private final UserRepository userRepository;
    private final TakePillsRepository takePillsRepository;

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
     * @author: 최서현xz
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
     * 사용자의 모든 복용 리스트 조회
     *
     * @author: 김보경
     * @param:
     * @return: TakeListResponse
     */
    public List<TakeListResponse> getTakeList(TakeListRequest takeListRequest) {
        // period t/f 별 기준일자 분기
        LocalDateTime baseDate;
        if(takeListRequest.period()) {
            baseDate = LocalDateTime.now().minusMonths(1);
        } else {
            baseDate = LocalDateTime.of(1910, 1, 1,0,0,0);
        }

        //===임시 User 객체 사용
        User user = userRepository.findById(1L).get();
        //===

        List< TakeList> takeLists = takeListRepository.findByUserAndFinishDateGreaterThanEqualOrIsFinish(user, baseDate, false);
        List<TakeListResponse> results = new ArrayList<>();

        for (TakeList takeList : takeLists) {
            List<TakePills> pills = takePillsRepository.findByTakeList(takeList);
            for (TakePills takePills : pills) {
                Pill pill = takePills.getPill();
                TakeListResponse takeListResponse = new TakeListResponse(
                        takeList.getTakeListId(),
                        takeList.getTakeListName(),
                        takeList.getCommonData(),
                        takeList.getFinishDate(),
                        takeList.getIsFinish(),
                        pill.getPillId(),
                        pill.getName(),
                        pill.getImageUrl(),
                        pill.getType(),
                        pill.getWarningPregnant(),
                        pill.getWarningUseDate(),
                        pill.getWarningElders(),
                        pill.getWarningTogether()
                );
                results.add(takeListResponse);
            }
        }

        return results;

    }
}

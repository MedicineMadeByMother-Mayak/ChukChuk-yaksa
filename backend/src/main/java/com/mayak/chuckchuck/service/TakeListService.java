package com.mayak.chuckchuck.service;

import com.mayak.chuckchuck.domain.TakeList;
import com.mayak.chuckchuck.exception.ErrorCode.CommonErrorCode;
import com.mayak.chuckchuck.exception.RestApiException;
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

}

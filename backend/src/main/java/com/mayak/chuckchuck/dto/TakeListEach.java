package com.mayak.chuckchuck.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mayak.chuckchuck.domain.TakeList;
import com.mayak.chuckchuck.domain.TakePills;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record TakeListEach(
        Long takeListId,
        String takeListName,
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDateTime createDate,
        LocalDateTime finishDate,
        boolean isFinished,
        List<TakeListPillInfo> takeListPillInfoList
) {
    public static TakeListEach createTakeListEach(TakeList takeList, List<TakePills> takePillsList){
        List<TakeListPillInfo> takeListPillInfos = takePillsList.stream()
                .map(takePills -> new TakeListPillInfo(
                        takePills.getPill().getPillId(),
                        takePills.getPill().getName(),
                        takePills.getPill().getImageUrl(),
                        takePills.getPill().getType(),
                        takePills.getPill().getWarningPregnant(),
                        takePills.getPill().getWarningUseDate(),
                        takePills.getPill().getWarningElders(),
                        takePills.getPill().getWarningTogether()))
                .collect(Collectors.toList());
        return new TakeListEach(
                takeList.getTakeListId(),
                takeList.getTakeListName(),
                takeList.getCommonData().getCreateDate(),
                takeList.getFinishDate(),
                takeList.getIsFinish(),
                takeListPillInfos
        );
    }
}

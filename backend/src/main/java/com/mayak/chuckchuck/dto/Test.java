package com.mayak.chuckchuck.dto;

import com.mayak.chuckchuck.domain.TakeList;
import com.mayak.chuckchuck.domain.TakePills;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record Test(
        Long takeListId,
        String takeListName,
        LocalDateTime createDate,
        LocalDateTime finishDate,
        boolean isFinished,
        List<TakeListPillInfo> takeListPillInfoList
) {
    public static Test createTest(TakeList takeList, List<TakePills> takePillsList){
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
        return new Test(
                takeList.getTakeListId(),
                takeList.getTakeListName(),
                takeList.getCommonData().getCreateDate(),
                takeList.getFinishDate(),
                takeList.getIsFinish(),
                takeListPillInfos
        );
    }
}

package com.mayak.chuckchuck.dto.response;

import com.mayak.chuckchuck.domain.CommonData;
import com.mayak.chuckchuck.domain.TakeList;
import com.mayak.chuckchuck.dto.TakeListInfo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record TakeListResponse(List<TakeListInfo> results) {
    public static TakeListResponse fromEntity(List<TakeList> takeLists) {
        List<TakeListInfo> results = takeLists.stream()
                .map(takeList -> new TakeListInfo(takeList.getTakeListId(), takeList.getTakeListName(), takeList.getCommonData(), takeList.getFinishDate(), takeList.getIsFinish()))
                .collect(Collectors.toList());
        return new TakeListResponse(results);
    }
}



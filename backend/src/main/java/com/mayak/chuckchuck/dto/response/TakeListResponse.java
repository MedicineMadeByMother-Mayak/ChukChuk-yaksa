package com.mayak.chuckchuck.dto.response;

import com.mayak.chuckchuck.domain.CommonData;

import java.time.LocalDateTime;
import java.util.Date;

public record TakeListResponse(
        // TakeList 의 컬럼
        Long takeListId,
        String takeListName,
//        LocalDateTime createDate, => private access 안됨
        CommonData commonData,
        LocalDateTime finishDate,
        Boolean isFinished,
        // Pills 의 컬럼
        Long pillId,
        String name,
        String imageUrl,
        String type,
        boolean warningPregnant,
        boolean warningUseDate,
        boolean warningElders,
        boolean warningTogether
) {
//    public TakeListResponse {
//        this(takeListId, takeListName, commonData, finishDate, isFinished, pillId, name, imageUrl, type, warningPregnant, warningUseDate, warningElders, warningTogether);
//    }
}

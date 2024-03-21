package com.mayak.chuckchuck.dto.response;

import com.mayak.chuckchuck.domain.CommonData;
import com.mayak.chuckchuck.domain.TakeList;
import com.mayak.chuckchuck.dto.TakeListInfo;
import com.mayak.chuckchuck.dto.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record TakeListResponse(List<Test> results) {
    public static TakeListResponse fromEntity(List<Test> testList) {

        return new TakeListResponse(testList);
    }
}



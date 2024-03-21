package com.mayak.chuckchuck.dto.response;

import com.mayak.chuckchuck.dto.TakeListEach;

import java.util.List;

public record TakeListResponse(List<TakeListEach> results) {
    public static TakeListResponse fromEntity(List<TakeListEach> testListEach) {
        return new TakeListResponse(testListEach);
    }
}



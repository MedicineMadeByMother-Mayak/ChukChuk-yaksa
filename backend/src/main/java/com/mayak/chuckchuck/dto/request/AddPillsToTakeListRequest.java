package com.mayak.chuckchuck.dto.request;

import java.util.List;

/**
 * 복용 리스트 약 추가
 * 에서 추가할 약ID의 목록 pills
 * @author:김보경
 */
public record AddPillsToTakeListRequest(List<Long> pills) {
}


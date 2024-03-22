package com.mayak.chuckchuck.dto.request;

import java.util.List;

/**
 * 복용 리스트 약 추가
 * 추가할 약 ID의 목록 pills 를 record로 선언
 * @author:김보경
 */
public record AddPillsToTakeListRequest(List<Long> pills) {
}


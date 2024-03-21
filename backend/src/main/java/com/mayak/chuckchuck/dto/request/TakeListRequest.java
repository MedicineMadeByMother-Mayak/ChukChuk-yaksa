package com.mayak.chuckchuck.dto.request;

public record TakeListRequest(
        /**
         * 복용리스트 조회에서의 입력값
         * @author:김보경
         period=false : 복용리스트
         period=true : 문진표
         */
        Boolean period
) {}
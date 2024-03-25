package com.mayak.chuckchuck.dto;


import lombok.Getter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@Getter
public class PagingDto {
    public PagingDto(int page, String standard) {
        // 페이지 번호, 페이지당 글 개수, 정렬 방향, 정렬 기준
        page -= 1;
        pageable = PageRequest.of(page, PAGE_SIZE, Sort.Direction.ASC, standard);
    }
    private final int PAGE_SIZE = 5;
    private final PageRequest pageable; // 페이징용 변수
}

package com.mayak.chuckchuck.service;

import com.mayak.chuckchuck.domain.Pill;
import com.mayak.chuckchuck.dto.PagingDto;
import com.mayak.chuckchuck.dto.response.PillSearchResultResp;
import com.mayak.chuckchuck.repository.PillRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class PillService {
    private final PillRepository pillRepository;
    /**
     * 이름순으로 약 페이징해서 가져오기
     *
     * @param page
     * @author: 최서현
     * @param: page 페이지번호
     * @return:
     */
    public PillSearchResultResp getLatestResult(int page) {
        PagingDto pagingDto = new PagingDto(page, "name");
        Page<Pill> latestResult = pillRepository.findAll(pagingDto.getPageable());
        Long totalCount = pillRepository.count();
        return PillSearchResultResp.fromEntity(latestResult, totalCount);
    }


    /**
     * 이름순으로 약 검색&페이징해서 가져오기
     * @author: 최서현
     * @param:
     * @return:
     */
    public PillSearchResultResp getSearchResult(String keyword, int page) {
        PagingDto pagingDto = new PagingDto(page, "name");
        Page<Pill> searchResult = pillRepository.findByNameContaining(keyword, pagingDto.getPageable());
        Long totalCount = pillRepository.countByNameContaining(keyword);
        return PillSearchResultResp.fromEntity(searchResult, totalCount);
    }
}

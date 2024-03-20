package com.mayak.chuckchuck.controller;

import com.mayak.chuckchuck.dto.response.PillSearchResultResp;
import com.mayak.chuckchuck.service.PillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/pill")
@RequiredArgsConstructor
public class PillController {
    private final PillService pillService;
    /**
     * 약 사진 AI검색
     * @author:
     * @param:
     * @return:
     */

    /**
     * 약 검색
     * @author: 최서현
     * @param:
     * @return:
     */
    @GetMapping("/search")
    public ResponseEntity<PillSearchResultResp> searchPill(@RequestParam(name="keyword") String keyword, @RequestParam(name="page", defaultValue = "1") int page){
        //키워드 없을경우 최근 등록된 알약 가져오기
        if(keyword==null) return ResponseEntity.ok(pillService.getLatestResult(page));
        //키워드가 있을경우 해당 데이터 가져오기
        else return ResponseEntity.ok(pillService.getSearchResult(keyword,page));
    }

    /**
     * 약 상세보기
     * @author:
     * @param:
     * @return:
     */
}

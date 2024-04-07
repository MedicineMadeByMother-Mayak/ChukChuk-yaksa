package com.mayak.chuckchuck.controller;

import com.mayak.chuckchuck.dto.response.PillDetailInfoResultResponse;
import com.mayak.chuckchuck.dto.response.PillSearchResultResponse;
import com.mayak.chuckchuck.service.CommonService;
import com.mayak.chuckchuck.service.PillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<PillSearchResultResponse> searchPill(@RequestParam(name="keyword") String keyword, @RequestParam(name="page", defaultValue = "1") int page){
        //키워드 없을경우 최근 등록된 알약 가져오기
        if(keyword==null) return ResponseEntity.ok(pillService.getLatestResult(page));
        //키워드가 있을경우 해당 데이터 가져오기
        else return ResponseEntity.ok(pillService.getSearchResult(keyword,page));
    }

    /**
     * 약 상세보기
     * @author 최진학
     * @param
     * @return
     */
    @GetMapping("/detail/{pillId}")
    public ResponseEntity<PillDetailInfoResultResponse> getPillDetailInfo(@PathVariable Long pillId) {
        return ResponseEntity.ok(pillService.getPillDetailInfoResult(pillId));
    }
}

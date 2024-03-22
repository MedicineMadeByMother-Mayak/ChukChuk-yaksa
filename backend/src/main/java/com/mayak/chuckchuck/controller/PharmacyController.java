package com.mayak.chuckchuck.controller;

import com.mayak.chuckchuck.dto.response.PharmacyResponse;
import com.mayak.chuckchuck.service.PharmacyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/pharmacy-search")
@RequiredArgsConstructor
public class PharmacyController {
    private final PharmacyService pharmacyService;

    /**
     * 약을 소유한 약국조회
     * @author: 차현철
     * @param: String city, String area, Long pillId, int page
     * @return: PharmacyResponse
     */
    @GetMapping
    public ResponseEntity<PharmacyResponse> pharmacySearch(@RequestParam String city, @RequestParam String area, @RequestParam Long pillId, @RequestParam int page){
        // 타이레놀 검색
        PharmacyResponse pharmacyResponse = PharmacyResponse.fromArr(pharmacyService.tylenolSearch(city, area));
        return ResponseEntity.ok(pharmacyResponse);
    }
}

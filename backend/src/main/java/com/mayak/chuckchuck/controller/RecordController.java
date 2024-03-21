package com.mayak.chuckchuck.controller;

import com.mayak.chuckchuck.dto.response.DiagnosisResponse;
import com.mayak.chuckchuck.dto.response.DiseaseResponse;
import com.mayak.chuckchuck.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/record")
@RequiredArgsConstructor
//기록(약봉투, 진단서, 병력, 약력)과 관련한 컨트롤러
public class RecordController {
    private final RecordService recordService;
    /**
     * 약봉투 OCR 결과
     * @author:
     * @param:
     * @return:
     */

    /**
     * 진단서 OCR 결과
     * @author: 김태완
     * @param: page
     * @return:
     */
    @GetMapping("/diagnosis")
    public ResponseEntity<DiagnosisResponse> getDiagnosisList(@RequestParam final int page){
        DiagnosisResponse diagnosisResponse = recordService.getDiagnosisList(page - 1);
        return ResponseEntity.ok(diagnosisResponse);
    }

    /**
     * 진단내역 조회
     * @author:
     * @param:
     * @return:
     */

    /**
     * 처방내역(약봉투)조회
     * @author:
     * @param:
     * @return:
     */

    /**
     * 병력조회
     * @author: 김태완
     * @param:
     * @return:
     */
    @GetMapping("/disease")
    public ResponseEntity<DiseaseResponse> getDiseaseList() {
        DiseaseResponse diseaseResponse = recordService.getDiseaseResponse();
        return ResponseEntity.ok(diseaseResponse);
    }

    /**
     * 약력조회
     * @author:
     * @param:
     * @return:
     */
}

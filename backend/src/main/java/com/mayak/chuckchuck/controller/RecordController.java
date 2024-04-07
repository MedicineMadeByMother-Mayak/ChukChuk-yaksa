package com.mayak.chuckchuck.controller;

import com.mayak.chuckchuck.domain.User;
import com.mayak.chuckchuck.dto.request.DiagnosisInfoResquest;
import com.mayak.chuckchuck.dto.request.PillBagInfoRequest;
import com.mayak.chuckchuck.dto.response.*;
import com.mayak.chuckchuck.enums.OcrType;
import com.mayak.chuckchuck.exception.ErrorCode.CommonErrorCode;
import com.mayak.chuckchuck.exception.RestApiException;
import com.mayak.chuckchuck.security.oauth2.user.UserPrincipal;
import com.mayak.chuckchuck.service.CommonService;
import com.mayak.chuckchuck.service.DiagnosisService;
import com.mayak.chuckchuck.service.OCRService;
import com.mayak.chuckchuck.service.PillBagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/record")
@RequiredArgsConstructor
@Slf4j
//기록(약봉투, 진단서, 병력, 약력)과 관련한 컨트롤러
public class RecordController {

    private final CommonService commonService;
    private final PillBagService pillBagService;
    private final DiagnosisService diagnosisService;
    private final OCRService ocrService;

    /**
     * 약봉투 OCR 결과
     * @author: 최서현
     * @param:
     * @return:
     */
    @PostMapping("/ocr/pill-bag")
    public ResponseEntity<PillBagOCRInfoResponse> ocrPillBag(@RequestBody MultipartFile file) {
        if(file==null || file.isEmpty()) throw new RestApiException(CommonErrorCode.FILE_NOT_FOUND);
        return ResponseEntity.ok((PillBagOCRInfoResponse) ocrService.ocrResult(OcrType.PILLBAG, file));
    }

    /**
     * 약봉투 내역 저장
     * @author: 최서현
     * @param:
     * @return:
     */
    @PostMapping("/pill-bag")
    public ResponseEntity<HttpStatus> registPillBag(@AuthenticationPrincipal UserPrincipal principal, @RequestBody PillBagInfoRequest pillBagInfo) {
        User user = commonService.getUserOrException(principal);
        pillBagService.registPillBag(user, pillBagInfo);
        return ResponseEntity.ok().build();
    }

    /**
     * 진단서 OCR 결과
     * @author:
     * @param:
     * @return:
     */
    @PostMapping("/ocr/diagnosis")
    public ResponseEntity<DiagnosisOCRInfoResponse> ocrDiagnosis(@RequestBody MultipartFile file) {
        if(file.isEmpty()) throw new RestApiException(CommonErrorCode.FILE_NOT_FOUND);
        return ResponseEntity.ok((DiagnosisOCRInfoResponse) ocrService.ocrResult(OcrType.DIAGNOSIS, file));
    }

    /**
     * 진단서 내역 저장
     * @author: 최서현
     * @param:
     * @return:
     */
    @PostMapping("/diagnosis")
    public ResponseEntity<HttpStatus> registDianosis(@AuthenticationPrincipal UserPrincipal principal, @RequestBody DiagnosisInfoResquest gianosisInfo) {
        User user = commonService.getUserOrException(principal);
        diagnosisService.registDianosis(user, gianosisInfo);
        return ResponseEntity.ok().build();
    }

    /**
     * 진단내역 조회
     * @author: 김태완
     * @param: page
     * @return:
     */
    @GetMapping("/diagnosis")
    public ResponseEntity<DiagnosisResponse> getDiagnosisList(@AuthenticationPrincipal UserPrincipal principal, @RequestParam final int page){
        User user = commonService.getUserOrException(principal);
        DiagnosisResponse diagnosisResponse = diagnosisService.getDiagnosisList(user, page - 1);
        return ResponseEntity.ok(diagnosisResponse);
    }

    /**
     * 처방내역(약봉투)조회
     * @author:
     * @param:
     * @return:
     */
    @GetMapping("/pill-bag")
    public ResponseEntity<PillBagResponse> getPillBagList(@AuthenticationPrincipal UserPrincipal principal, @RequestParam final int page){
        User user = commonService.getUserOrException(principal);
        PillBagResponse pillBagResponse = pillBagService.getPillBagResponse(user, page);
        return ResponseEntity.ok(pillBagResponse);
    }

    /**
     * 병력조회
     * @author: 김태완
     * @param:
     * @return:
     */
    @GetMapping("/disease")
    public ResponseEntity<DiseaseResponse> getDiseaseList(@AuthenticationPrincipal UserPrincipal principal) {
        User user = commonService.getUserOrException(principal);
        DiseaseResponse diseaseResponse = diagnosisService.getDiseaseResponse(user);
        return ResponseEntity.ok(diseaseResponse);
    }

}

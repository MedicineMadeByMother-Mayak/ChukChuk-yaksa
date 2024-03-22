package com.mayak.chuckchuck.service;

import com.mayak.chuckchuck.domain.Diagnosis;
import com.mayak.chuckchuck.domain.Pill;
import com.mayak.chuckchuck.domain.PillBag;
import com.mayak.chuckchuck.domain.User;
import com.mayak.chuckchuck.dto.PagingDto;
import com.mayak.chuckchuck.dto.PrescriptionInfoDto;
import com.mayak.chuckchuck.dto.response.DiagnosisResponse;
import com.mayak.chuckchuck.dto.response.DiseaseResponse;
import com.mayak.chuckchuck.dto.response.PillBagResponse;
import com.mayak.chuckchuck.dto.response.PrescriptionInfoResponse;
import com.mayak.chuckchuck.exception.ErrorCode.CommonErrorCode;
import com.mayak.chuckchuck.exception.RestApiException;
import com.mayak.chuckchuck.ocr.NaverOcrApi;
import com.mayak.chuckchuck.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class  RecordService {
    @Value("${naver.service.secretKey}")
    private String secretKey;
    private final NaverOcrApi naverOcrApi;

    private final DiagnosisRepository diagnosisRepository;
    private final UserRepository userRepository;
    private final PillBagRepository pillBagRepository;
    private final OCRPillsRepository ocrPillsRepository;
    private final PillRepository pillRepository;

    /**
     * ocr 검사결과
     * @author: 최서현
     * @param: file
     * @return: List<String>
     */
    public PrescriptionInfoResponse ocrResult(MultipartFile file){
        try{
            File tempFile = File.createTempFile("temp", file.getOriginalFilename()); //임시파일객체 생성
            file.transferTo(tempFile); //업로드된 파일을 임시파일객체에 넣어줌
            List<String> ocrResult = naverOcrApi.callApi("POST", tempFile.getPath(), secretKey, "jpg");
            tempFile.delete(); // 임시 파일 삭제

            if(ocrResult == null) throw  new RestApiException(CommonErrorCode.RESULT_NOT_FOUND);

            HashMap<String, Object> validOcrResult = validOcr(ocrResult);

            return PrescriptionInfoResponse.fromValidOcrResult(validOcrResult);

        } catch (IOException e){
            log.error(e.getMessage());
            throw new RestApiException(CommonErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    //유효기록만 분리
    public HashMap<String, Object> validOcr(List<String> ocrList) {
        HashMap<String,Object> validResult = new HashMap<>();
        for(int i=0; i<ocrList.size(); i++){
            if(ocrList.get(i).equals("조제일자")) validResult.put("buildDate",ocrList.get(i+1));
            else if(ocrList.get(i).equals("상호")) validResult.put("pharmacyName",ocrList.get(i+1));
            else if(ocrList.get(i).equals("합") && ocrList.get(i+1).equals("계")) {
                validResult.put("cost",ocrList.get(i+2));
            }
            else if(ocrList.get(i).toCharArray()[0] == '['){
                String pillName = ocrList.get(i-1); //약이름
                String guide = ocrList.get(i+1); //안내

                PagingDto pagingDto = new PagingDto(1, "name");
                Page<Pill> searchResult = pillRepository.findByNameContaining(pillName, pagingDto.getPageable());
                if(searchResult.isEmpty()) {
                    //==현재 약 데이터가 부족해 임시코드를 작성
                    pillName = "활명수";
                    searchResult = pillRepository.findByNameContaining(pillName, pagingDto.getPageable());
                    //==

                    //==나중에 위 임시코드 지우고 아래 컨티뉴를 활성화하자
                    //continue;
                }
                Pill pill = searchResult.getContent().get(0);
                PrescriptionInfoDto prescriptionInfoDto = PrescriptionInfoDto.fromEntityAndGuide(pill,guide);

                if (!validResult.containsKey("prescriptionInfo")) {
                    validResult.put("prescriptionInfo", new ArrayList<PrescriptionInfoDto>());
                }
                if (validResult.get("prescriptionInfo") instanceof ArrayList) {
                    ((ArrayList<PrescriptionInfoDto>) validResult.get("prescriptionInfo")).add(prescriptionInfoDto);
                }

            }
        }
        return validResult;
    }

    /**
     * 진단 기록 조회
     * @author: 김태완
     * @param: page 페이지 번호
     * @return: DiagnosisResponse
     */
    public DiagnosisResponse getDiagnosisList(int page) {
        User user = userRepository.findById(1L).get();
        Pageable pageable = PageRequest.of(page, 5, Sort.Direction.DESC, "diagnosisDate");

        Page<Diagnosis> diagnosisPage = diagnosisRepository.findAllByUser(user, pageable);
        return DiagnosisResponse.fromEntity(diagnosisPage);
    }

    /**
     * 병력 조회
     * @author: 김태완
     * @param:
     * @return: DiseaseResponse
     */
    public DiseaseResponse getDiseaseResponse() {
        User user = userRepository.findById(1L).get();
        List<Diagnosis> dieaseList = diagnosisRepository.findAllByUser(user);
        return DiseaseResponse.fromEntity(dieaseList);
    }

    public PillBagResponse getPillBagResponse(int page) {
        User user = userRepository.findById(1L).get();
        PagingDto pagingDto = new PagingDto(page, "buildDate");
        Page<PillBag> pillBags = pillBagRepository
                .findByUser(user, pagingDto.getPageable());
        List<PillBagResponse.PillBagDto> pillBagResult = pillBags.getContent().stream()
                .map(pillBag -> {
                    PillBagResponse.PillBagReceipt receipt = PillBagResponse.fromReceipt(pillBag);
                    List<PillBagResponse.OCRPillsGuide> guide = ocrPillsRepository.findByOcrListOcrId(pillBag.getOcrId()).stream()
                        .map(PillBagResponse::fromGuide).toList();

                return new PillBagResponse.PillBagDto(receipt, guide);
            })
            .collect(Collectors.toList());
        return PillBagResponse.fromEntity((int) pillBags.getTotalElements(), pillBagResult);
    }
}

package com.mayak.chuckchuck.service;

import com.mayak.chuckchuck.domain.Pill;
import com.mayak.chuckchuck.dto.PagingDto;
import com.mayak.chuckchuck.dto.PrescriptionInfoDto;
import com.mayak.chuckchuck.dto.response.DiagnosisOCRInfoResponse;
import com.mayak.chuckchuck.dto.response.PillBagOCRInfoResponse;
import com.mayak.chuckchuck.enums.OcrType;
import com.mayak.chuckchuck.exception.ErrorCode.CommonErrorCode;
import com.mayak.chuckchuck.exception.RestApiException;
import com.mayak.chuckchuck.repository.PillRepository;
import com.mayak.chuckchuck.utill.NaverOcrApi;
import com.mayak.chuckchuck.utill.OpenApiManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class OCRService {

    @Value("${naver.service.secretKey}")
    private String secretKey;
    private final NaverOcrApi naverOcrApi;
    private final PillRepository pillRepository;
    private final OpenApiManager openApiManager;


    /**
     * ocr 검사결과
     * @author: 최서현
     * @param: file
     * @return: List<String>
     */
    public Object ocrResult(OcrType type, MultipartFile file){
        try{
            File tempFile = File.createTempFile("temp", file.getOriginalFilename()); //임시파일객체 생성
            file.transferTo(tempFile); //업로드된 파일을 임시파일객체에 넣어줌
            List<String> ocrResult = naverOcrApi.callApi("POST", tempFile.getPath(), secretKey, "jpg");
            tempFile.delete(); // 임시 파일 삭제

            if(ocrResult == null) throw  new RestApiException(CommonErrorCode.RESULT_NOT_FOUND);

            switch (type) {
                case PILLBAG:
                    HashMap<String, Object> validPillBagResult = validPillBag(ocrResult);
                    return PillBagOCRInfoResponse.fromValidOcrResult(validPillBagResult);

                case DIAGNOSIS:
                    HashMap<String, String> validDiagnosisResult = validDiagnosis(ocrResult);
                    return DiagnosisOCRInfoResponse.fromValidOcrResult(validDiagnosisResult);
            }


        } catch (IOException e){
            log.error(e.getMessage());
            throw new RestApiException(CommonErrorCode.INTERNAL_SERVER_ERROR);
        }
        return null;
    }

    /**
     * 진단서 OCR 결과에서 유효기록만 분리
     * @author: 최서현
     * @param: ocrList
     * @return: HashMap<String, Object> 유효기록 해시맵
     */
    private HashMap<String, String> validDiagnosis(List<String> ocrList) {
        String datePattern = "\\b\\d{4}[-년]\\d{1,2}[-월]\\d{1,2}일?\\b";
        Pattern dateRegex = Pattern.compile(datePattern);
        String illCodePattern = "[A-Z][0-9]{1,3}";
        Pattern illCodeRegex = Pattern.compile(illCodePattern);

        HashMap<String,String> validResult = new HashMap<>();
        for(int i=0; i<ocrList.size(); i++){

            //진단일 추출
            Matcher dateMatcher = dateRegex.matcher(ocrList.get(i));
            if(dateMatcher.find()) validResult.put("diagnosisDate",ocrList.get(i));

            //질병코드 추출
            Matcher illCodeMatcher = illCodeRegex.matcher(ocrList.get(i));
            if(illCodeMatcher.find()) {
                if (validResult.containsKey("illCode")) validResult.put("illCode",validResult.get("illCode")+"\n"+ocrList.get(i));
                else validResult.put("illCode",ocrList.get(i));
                // 진단코드로 부터 진단명을 공공데이터에 요청
                String illName = openApiManager.getIllNamethroughOpenApi(ocrList.get(i));
                if(illName != null) validResult.put("illName", (validResult.getOrDefault("illName", ""))+"\n"+illName);
            }

            //병원명 추출
            else if(ocrList.get(i).contains("의원")) validResult.put("hospitalName",ocrList.get(i));
            else if(ocrList.get(i).contains("병원")) validResult.put("hospitalName",ocrList.get(i));
            else if(ocrList.get(i).contains("내과")) validResult.put("hospitalName",ocrList.get(i));
            else if(ocrList.get(i).contains("이비인후과")) validResult.put("hospitalName",ocrList.get(i));
            else if(ocrList.get(i).contains("피부과")) validResult.put("hospitalName",ocrList.get(i));
            else if(ocrList.get(i).contains("치과")) validResult.put("hospitalName",ocrList.get(i));

            //소견
            else if(ocrList.get(i).equals("치료내용")) validResult.put("opinion",ocrList.get(i+1));
            else if(ocrList.get(i).equals("소견")) validResult.put("opinion",ocrList.get(i+1));



        }



        return validResult;
    }

    /**
     * 약봉투 OCR 결과에서 유효기록만 분리
     * @author: 최서현
     * @param: ocrList
     * @return: HashMap<String, Object> 유효기록 해시맵
     */
    public HashMap<String, Object> validPillBag(List<String> ocrList) {
        HashMap<String, Object> validResult = new HashMap<>();
        for (int i = 0; i < ocrList.size(); i++) {
            if (ocrList.get(i).equals("조제일자")) validResult.put("buildDate", ocrList.get(i + 1));
            else if (ocrList.get(i).equals("상호")) validResult.put("pharmacyName", ocrList.get(i + 1));
            else if (ocrList.get(i).equals("합") && ocrList.get(i + 1).equals("계")) {
                validResult.put("cost", ocrList.get(i + 2));
            } else if (ocrList.get(i).toCharArray()[0] == '[') {
                String pillName = ocrList.get(i - 1); //약이름
                String guide = ocrList.get(i + 1); //안내

                PagingDto pagingDto = new PagingDto(1, "name");
                Optional<Page<Pill>> searchResult = pillRepository.findByNameContaining(pillName, pagingDto.getPageable());

                Pill pill;
                PrescriptionInfoDto prescriptionInfoDto;
                if (searchResult.isPresent() && !searchResult.get().isEmpty()) {
                    //검색결과 있음 -> 약이 척척약사 DB에 있다.
                    pill = searchResult.get().getContent().get(0);
                    prescriptionInfoDto = PrescriptionInfoDto.fromEntityAndGuide(pill, guide);
                } else{
                    //검색결과 없음 -> 약이 척척약사 DB에 없다.
                    prescriptionInfoDto = PrescriptionInfoDto.fromNoDataEntity(pillName);
                }


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
}

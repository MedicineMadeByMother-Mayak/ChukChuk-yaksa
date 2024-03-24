package com.mayak.chuckchuck.dto.response;

import java.time.LocalDateTime;
import java.util.HashMap;

public record DiagnosisOCRInfoResponse(LocalDateTime diagnosisDate,
                               String hospitalName,
                               String illCode,
                               String illName,
                               String opinion) {

    public static DiagnosisOCRInfoResponse fromValidOcrResult(HashMap<String, String> validDiagnosisResult){

        //진단서 내용을 유효한 데이터값으로 변환
        dataToValid(validDiagnosisResult);

        //진단일이 있을경우
        String diagnosisLocalDate = validDiagnosisResult.get("diagnosisDate");
        if(!diagnosisLocalDate.equals("")) {
            LocalDateTime diagnosisDateTime = LocalDateTime.parse(diagnosisLocalDate + "T00:00:00");
            return new DiagnosisOCRInfoResponse(diagnosisDateTime,validDiagnosisResult.get("hospitalName"),validDiagnosisResult.get("illCode"),
                    validDiagnosisResult.get("illName"),validDiagnosisResult.get("opinion"));
        } else{ //진단일이 없을경우
            return new DiagnosisOCRInfoResponse(null,validDiagnosisResult.get("hospitalName"),validDiagnosisResult.get("illCode"),
                    validDiagnosisResult.get("illName"),validDiagnosisResult.get("opinion"));
        }


    }

    /**
     * 약봉투 OCR 결과에서 유효기록만 분리
     * @author: 최서현
     * @param: HashMap<String, String> validDiagnosisResult 진단서 OCR 분석기록
     * @return:
     */
    private static void dataToValid(HashMap<String, String> validDiagnosisResult){
        String diagnosisDate = validDiagnosisResult.get("diagnosisDate");
        if(diagnosisDate!=null){
            diagnosisDate = diagnosisDate.replaceAll(" ", "");
            if(diagnosisDate.contains("년")) {
                diagnosisDate = diagnosisDate.replaceAll("년", "-");
                diagnosisDate = diagnosisDate.replaceAll("월", "-");
                diagnosisDate = diagnosisDate.replaceAll("일", "-");
                validDiagnosisResult.put("diagnosisDate", diagnosisDate);
            }
        }

        validDiagnosisResult.putIfAbsent("diagnosisDate", "");
        validDiagnosisResult.putIfAbsent("hospitalName", "");
        validDiagnosisResult.putIfAbsent("illCode", "");
        validDiagnosisResult.putIfAbsent("illName", "");
        validDiagnosisResult.putIfAbsent("opinion", "");
    }


}

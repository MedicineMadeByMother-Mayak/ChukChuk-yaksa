package com.mayak.chuckchuck.service;

import com.mayak.chuckchuck.domain.Diagnosis;
import com.mayak.chuckchuck.dto.response.DiagnosisResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.mayak.chuckchuck.domain.User;
import com.mayak.chuckchuck.repository.DiagnosisRepository;
import com.mayak.chuckchuck.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class  RecordService {
    private final DiagnosisRepository diagnosisRepository;
    private final UserRepository userRepository;

    /**
     * 진단 기록 조회
     * @author: 김태완
     * @param: page 페이지 번호
     * @return: DiagnosisResponse
     */
    public DiagnosisResponse getDiagnosisList(int page) {
        User user = userRepository.findById(1L).get();
        Pageable pageable = PageRequest.of(page, 5);

        Page<Diagnosis> diagnosisPage = diagnosisRepository.findByUser(user, pageable);
        return DiagnosisResponse.fromEntity(page, diagnosisPage);
    }
}

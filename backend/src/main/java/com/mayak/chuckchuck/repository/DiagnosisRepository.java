package com.mayak.chuckchuck.repository;

import com.mayak.chuckchuck.domain.Diagnosis;
import com.mayak.chuckchuck.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiagnosisRepository extends JpaRepository<Diagnosis, Long> {

    /**
     * 로그인 유저의 진단 내역 리스트를 페이징하여 반환합니다.
     * @author: 김태완
     * @param user 유저 정보
     * @param pageable 페이지 정보
     * @return Page<OCRList> 페이징된 진단 내역 리스트
     */

    Page<Diagnosis> findByUser(User user, Pageable pageable);
}
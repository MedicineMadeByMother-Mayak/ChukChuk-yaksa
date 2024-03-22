package com.mayak.chuckchuck.repository;

import com.mayak.chuckchuck.domain.OCRList;
import com.mayak.chuckchuck.domain.OCRPills;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OCRPillsRepository extends JpaRepository<OCRPills, Long> {
//    List<OCRPills> findByOcrList(OCRList ocrList);
    List<OCRPills> findByOcrListOcrId(Long ocrId);
}

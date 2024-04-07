package com.mayak.chuckchuck.repository;

import com.mayak.chuckchuck.domain.Diagnosis;
import com.mayak.chuckchuck.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiagnosisRepository extends JpaRepository<Diagnosis, Long> {
    Page<Diagnosis> findAllByUser(User user, Pageable pageable);
    List<Diagnosis> findAllByUser(User user);
}
package com.mayak.chuckchuck.repository;

import com.mayak.chuckchuck.domain.Diagnosis;
import com.mayak.chuckchuck.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiseaseRepository extends JpaRepository<Diagnosis, Long> {
    List<Diagnosis> findAllByUser(User user);
}

package com.mayak.chuckchuck.repository;

import com.mayak.chuckchuck.domain.Pill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PillRepository extends JpaRepository<Pill, Long> {
    Page<Pill> findByNameContaining(String keyword, Pageable pageable);

    Long countByNameContaining(String keyword);
}

package com.mayak.chuckchuck.repository;

import com.mayak.chuckchuck.domain.Pill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PillRepository extends JpaRepository<Pill, Long> {
    Optional<Page<Pill>> findByNameContaining(String keyword, Pageable pageable);

    Long countByNameContaining(String keyword);

    @Query(value = "SELECT p FROM Pill p ORDER BY RAND() DESC")
    Pill findRandomPill();
}

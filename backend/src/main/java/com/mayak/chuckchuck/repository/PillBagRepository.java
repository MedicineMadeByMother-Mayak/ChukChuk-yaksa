package com.mayak.chuckchuck.repository;

import com.mayak.chuckchuck.domain.PillBag;
import com.mayak.chuckchuck.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PillBagRepository extends JpaRepository<PillBag, Long> {
    Page<PillBag> findByUser(User user, Pageable pageable);
}

package com.mayak.chuckchuck.repository;

import com.mayak.chuckchuck.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserPillEffectRepository extends JpaRepository<UserPillEffect, Long> {
    List<UserPillEffect> findByUserAndPill_pillId(User user, Long pillId);
    UserPillEffect findByUserAndPillAndCategory(User user, Pill pill, Category category);
}

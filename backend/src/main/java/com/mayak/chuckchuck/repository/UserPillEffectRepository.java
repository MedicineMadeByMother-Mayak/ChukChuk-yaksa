package com.mayak.chuckchuck.repository;

import com.mayak.chuckchuck.domain.Pill;
import com.mayak.chuckchuck.domain.TakeList;
import com.mayak.chuckchuck.domain.User;
import com.mayak.chuckchuck.domain.UserPillEffect;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserPillEffectRepository extends JpaRepository<UserPillEffect, Long> {
    List<UserPillEffect> findByUserAndPill_pillId(User user, Long pillId);
}

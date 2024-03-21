package com.mayak.chuckchuck.repository;

import com.mayak.chuckchuck.domain.UserPillEffectToTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserPillEffectToTagRepository extends JpaRepository<UserPillEffectToTag, Long> {
    List<UserPillEffectToTag> findByUserPillEffect_UserPillEffectId(long userPillEffectId);
}

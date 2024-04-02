package com.mayak.chuckchuck.repository;

import com.mayak.chuckchuck.domain.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserPillEffectRepository extends JpaRepository<UserPillEffect, Long> {
    List<UserPillEffect> findByUserAndPill_pillId(User user, Long pillId);
    UserPillEffect findByUserAndPillAndCategory(User user, Pill pill, Category category);
    List<UserPillEffect> findByUserAndCategory_CategoryIdOrderByCommonData_createDate(User user, Long categoryId);
    List<UserPillEffect> findByUserAndCategory_CategoryId(User user, Long categoryId);
    List<UserPillEffect> findByUserAndCommonData_IsDelete(User user, Pageable pageable, boolean isDelete);

}

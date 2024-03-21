package com.mayak.chuckchuck.repository;

import com.mayak.chuckchuck.domain.Pill;
import com.mayak.chuckchuck.domain.TakeList;
import com.mayak.chuckchuck.domain.User;
import com.mayak.chuckchuck.domain.UserPillEffect;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserPillEffectRepository extends JpaRepository<UserPillEffect, Long> {

    /**
     * pillId로 사용자가 등록한
     * @author: 최진학
     * @param: User
     * @return: TakeList
     */
//    List<UserPillEffect> findByUser_UserIdAndPill_PillId(Long userId, Long pillId);
    List<UserPillEffect> findByUserAndPill_pillId(User user, Long pillId);
}

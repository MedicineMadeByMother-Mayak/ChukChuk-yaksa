package com.mayak.chuckchuck.repository;

import com.mayak.chuckchuck.domain.User;
import com.mayak.chuckchuck.enums.SocialCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findBySocialCodeAndEmail(SocialCode socialCode, String email);
}

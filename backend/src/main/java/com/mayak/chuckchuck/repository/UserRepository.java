package com.mayak.chuckchuck.repository;

import com.mayak.chuckchuck.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}

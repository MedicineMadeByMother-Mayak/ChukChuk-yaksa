package com.mayak.chuckchuck.repository;

import com.mayak.chuckchuck.domain.TakeList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TakeListRepository extends JpaRepository<TakeList, Long> {
}

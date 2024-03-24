package com.mayak.chuckchuck.repository;

import com.mayak.chuckchuck.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}

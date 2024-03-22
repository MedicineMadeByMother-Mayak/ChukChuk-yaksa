package com.mayak.chuckchuck.repository;

import com.mayak.chuckchuck.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {

    List<Tag> findByUser_userIdAndCategory_CategoryId(Long userId, Long categoryId);
}

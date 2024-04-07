package com.mayak.chuckchuck.repository;

import com.mayak.chuckchuck.domain.Pill;
import com.mayak.chuckchuck.domain.Tag;
import com.mayak.chuckchuck.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {

    List<Tag> findByUser_userIdAndCategory_CategoryId(Long userId, Long categoryId);
}

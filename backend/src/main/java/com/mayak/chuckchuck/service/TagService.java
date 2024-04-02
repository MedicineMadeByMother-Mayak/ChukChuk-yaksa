package com.mayak.chuckchuck.service;

import com.mayak.chuckchuck.domain.Category;
import com.mayak.chuckchuck.domain.Tag;
import com.mayak.chuckchuck.domain.User;
import com.mayak.chuckchuck.repository.CategoryRepository;
import com.mayak.chuckchuck.repository.TagRepository;
import com.mayak.chuckchuck.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    /**
     * Tag 추가
     * @author 최진학
     * @param tagName (새로운 태그 이름)
     * @param categoryId (등록될 카테고리 ID)
     * @return
     */
    public Long reigstTag(User user, String tagName, Long categoryId) {

        Category category = categoryRepository.findById(categoryId).get();
        Tag tag = Tag.createTag(tagName, user, category);

        Tag savedTag = tagRepository.save(tag);
        return savedTag.getTagId();
    }
}

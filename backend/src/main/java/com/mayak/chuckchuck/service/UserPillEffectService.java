package com.mayak.chuckchuck.service;

import com.mayak.chuckchuck.domain.*;
import com.mayak.chuckchuck.dto.CategoryDto;
import com.mayak.chuckchuck.dto.TagDto;
import com.mayak.chuckchuck.dto.request.UserPillEffectMemoRequest;
import com.mayak.chuckchuck.dto.request.UserPillEffectRegistInfoRequest;
import com.mayak.chuckchuck.dto.response.UserPillEffectResponse;
import com.mayak.chuckchuck.repository.*;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserPillEffectService {
    private final UserPillEffectRepository userPillEffectRepository;
    private final UserPillEffectToTagRepository userPillEffectToTagRepository;
    private final UserRepository userRepository;
    private final PillRepository pillRepository;
    private final CategoryRepository categoryRepository;
    private final EntityManager entityManager;
    private final TagRepository tagRepository;

    /**
     * 약효기록 상세 조회 (인데 없으면 등록이라... reigst로 했는데 이름 진짜 맘에 안듦)
     * @author 최진학
     * @param pillId (
     * @return userPillEffectList (사용자 약효 기록 내역)
     */
    public UserPillEffectResponse registUserPillEffect(Long pillId) {
        // == 임시 유저 정보 (추후 변경 필요) ==
        User user = userRepository.findById(1L).get();
        // =================================

        // user의 약효기록 리스트 조회
        List<UserPillEffect> userPillEffectList = userPillEffectRepository.findByUserAndPill_pillId(user, pillId);

        // 약 기록이 없는 경우, 테이블에 삽입
        if (userPillEffectList.isEmpty()) {
            System.out.println("[System][UserPillEffectService][registUserPillEffect] 약 기록 내역 없음, DB에 입력 실행");
            Pill requestPill = pillRepository.findById(pillId).get();

            // 1. 부작용
            // 2. 중단
            // 3. 효과
            for (long categoryId = 1; categoryId <= 3; categoryId++) {
                UserPillEffect userPillEffect = new UserPillEffect();
                Category tempCategory = categoryRepository.findById(categoryId).get();

                userPillEffect.registUserPillEffect(user, tempCategory, requestPill);
                userPillEffectList.add(userPillEffect);
            }

            // 새로운 엔티티를 저장
            userPillEffectRepository.saveAll(userPillEffectList);
        }

        List<List<TagDto>> usedTags = new ArrayList<>();
        List<List<TagDto>> notUsedTags = new ArrayList<>();

        // 카테고리별 약 분류
        for (long categoryId = 1; categoryId <= 3; categoryId++) {
            // 현재 약에서 사용하는 태그들
            String jpqlQueryForUsedTags = "SELECT up.category.categoryId, t.tagId, t.tagName " +
                                            "FROM UserPillEffect up " +
                                                "JOIN UserPillEffectToTag uptt ON up.userPillEffectId = uptt.userPillEffect.userPillEffectId " +
                                                "JOIN Tag t ON uptt.tag.tagId = t.tagId " +
                                            "WHERE up.user.userId = :userId " +
                                                "AND up.pill.pillId = :pillId " +
                                                "AND up.category.categoryId = :categoryId ";


            // 현재 약에서 사용하지 않는 태그들
            String jpqlQueryForNotUsedTags = "SELECT up.category.categoryId, t.tagId, t.tagName " +
                                                "FROM UserPillEffect up " +
                                                    "JOIN UserPillEffectToTag uptt ON up.userPillEffectId = uptt.userPillEffect.userPillEffectId " +
                                                    "JOIN Tag t ON uptt.tag.tagId = t.tagId " +
                                                "WHERE up.user.userId = :userId " +
                                                    "AND up.pill.pillId != :pillId " +
                                                    "AND up.category.categoryId = :categoryId ";

            // 카테고리별 사용중인 태그들
            List<TagDto> tempUsedTags = entityManager.createQuery(jpqlQueryForUsedTags, TagDto.class)
                    .setParameter("categoryId", categoryId)
                    .setParameter("userId", 1L)
                    .setParameter("pillId", pillId)
                    .getResultList();

            // 카테고리별 사용중이지 않은 태그들
            List<TagDto> tempNotUsedTags = entityManager.createQuery(jpqlQueryForNotUsedTags, TagDto.class)
                    .setParameter("categoryId", categoryId)
                    .setParameter("userId", 1L)
                    .setParameter("pillId", pillId)
                    .getResultList();

            usedTags.add(tempUsedTags);
            notUsedTags.add(tempNotUsedTags);
        }

        return UserPillEffectResponse.fromEntity(userPillEffectList, usedTags, notUsedTags);
    }

    /**
     * 약효기록 삭제
     * @author 최진학
     * @param userPillEffectId (약효 기록 id)
     * @return 없음
     */
    public void updateUserPillEffectIsDelete(Long userPillEffectId) {
        UserPillEffect userPillEffect = userPillEffectRepository.findById(userPillEffectId).get();
        userPillEffect.getCommonData().toggleDelete();
    }

    /**
     * 약효기록 - 메모수정
     * @author 최진학
     * @param userPillEffectMemoRequest (약효 기록 메모 업데이트 request)
     * @return 없음
     */
    public void updateUserPillEffectMemo(UserPillEffectMemoRequest userPillEffectMemoRequest) {
        UserPillEffect userPillEffect = userPillEffectRepository.findById(userPillEffectMemoRequest.usePillEffectId()).get();
        userPillEffect.updateMemo(userPillEffectMemoRequest.memo());
    }

    /**
     * 약효기록 - 추가 (업데이트) & 태그 등록 기능
     * @author 최진학
     * @param userPillEffectRegistInfoRequest (약효 기록 업데이트 request)
     * @return 없음
     */
    public void updateUserPillEffect(UserPillEffectRegistInfoRequest userPillEffectRegistInfoRequest) {
        Long currentPillId = userPillEffectRegistInfoRequest.pillId();
        List<CategoryDto> categoryDtos = userPillEffectRegistInfoRequest.category();

        // == 임시 유저 정보 (추후 변경 필요) ==
        User user = userRepository.findById(1L).get();
        // =================================

        // 카테고리마다 약효 기록을 추가 해야함
        for (CategoryDto currentCategory : categoryDtos) {
            List<TagDto> currentTagDtos = currentCategory.usedTags();
            Category registCategory = categoryRepository.findById(currentCategory.categoryId()).get();
            Pill registPill = pillRepository.findById(currentPillId).get();  // 등록할 약
            UserPillEffect registUserpillEffect = userPillEffectRepository.findByUserAndPillAndCategory(user, registPill, registCategory);

            registUserpillEffect.updateMemo(currentCategory.memo());
            userPillEffectRepository.save(registUserpillEffect);

            // 카테고리 등록된 태그가 비어있지 않고, 0보다 크면 (=값이 있으면), 태그들 등록 (코드 개선 : peek 쓰면 바로 적용가능)
            if (!currentTagDtos.isEmpty()) {
                CommonData commonData = new CommonData();
                List<UserPillEffectToTag> userPillEffectToTags = currentTagDtos.stream()
                        .map(tempTagDto -> {
                            Tag tag = Tag.registTag(tempTagDto.tagId(), tempTagDto.tagName(), user, registCategory);
                            return tag;
                        })
                        .peek(tagRepository::save) // 태그 등록 or 업데이트
                        .map(tag -> {
                            UserPillEffectToTag userPillEffectToTag = new UserPillEffectToTag();
                            userPillEffectToTag.updateUserPillEffectToTag(registUserpillEffect, tag, commonData);
                            return userPillEffectToTag;
                        })
                        .toList();

                // 유저 사용 기록에 태그 등록 or 업데이트
                userPillEffectToTagRepository.saveAll(userPillEffectToTags);
            }
        }
    }
}

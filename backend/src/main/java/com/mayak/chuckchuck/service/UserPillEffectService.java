package com.mayak.chuckchuck.service;

import com.mayak.chuckchuck.domain.*;
import com.mayak.chuckchuck.dto.TagDto;
import com.mayak.chuckchuck.dto.request.UserPillEffectMemoRequest;
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
    private final UserRepository userRepository;
    private final PillRepository pillRepository;
    private final CategoryRepository categoryRepository;
    private final EntityManager entityManager;

    /**
     * 약효기록 상세 조회 (
     * @author 최진학
     * @param pillId (
     * @return userPillEffectList (사용자 약효 기록 내역)
     */
    public UserPillEffectResponse registUserPillEffect(Long pillId) {
        User user = userRepository.findById(1L).get();

        // user의 약효기록 리스트 조회
        List<UserPillEffect> userPillEffectList = userPillEffectRepository.findByUserAndPill_pillId(user, pillId);

        // 약 기록이 없는 경우, 테이블에 삽입
        if (userPillEffectList.isEmpty()) {
            System.out.println("[System][UserPillEffectService][registUserPillEffect] 약 기록 내역 없음, DB에 입력 실행");
            Pill requestPill = pillRepository.findById(pillId).get();

            // 1. 부작용
            // 2. 중단
            // 3. 효과
            for (long categoryIndex = 1; categoryIndex <= 3; categoryIndex++) {
                UserPillEffect userPillEffect = new UserPillEffect();
                Category tempCategory = categoryRepository.findById(categoryIndex).get();

                userPillEffect.registUserPillEffect(user, tempCategory, requestPill);
                userPillEffectList.add(userPillEffect);
            }

            // 새로운 엔티티를 저장
            userPillEffectRepository.saveAll(userPillEffectList);
        }

        List<List<TagDto>> usedTags = new ArrayList<>();
        List<List<TagDto>> notUsedTags = new ArrayList<>();

        // 카테고리별 약 분류
        for (long categoryIndex = 1; categoryIndex <= 3; categoryIndex++) {
            // 현재 약에서 사용하는 태그들
            String jpqlQueryForUsedTags = "SELECT up.userPillEffectId, up.category.categoryId, t.tagId, t.tagName " +
                                            "FROM UserPillEffect up " +
                                                "JOIN UserPillEffectToTag uptt ON up.userPillEffectId = uptt.userPillEffect.userPillEffectId " +
                                                "JOIN Tag t ON uptt.tag.tagId = t.tagId " +
                                            "WHERE up.user.userId = :userId " +
                                                "AND up.pill.pillId = :pillId " +
                                                "AND up.category.categoryId = :categoryIndex ";


            // 현재 약에서 사용하지 않는 태그들
            String jpqlQueryForNotUsedTags = "SELECT up.userPillEffectId, up.category.categoryId, t.tagId, t.tagName " +
                                                "FROM UserPillEffect up " +
                                                    "JOIN UserPillEffectToTag uptt ON up.userPillEffectId = uptt.userPillEffect.userPillEffectId " +
                                                    "JOIN Tag t ON uptt.tag.tagId = t.tagId " +
                                                "WHERE up.user.userId = :userId " +
                                                    "AND up.pill.pillId != :pillId " +
                                                    "AND up.category.categoryId = :categoryIndex ";

            List<Object[]> resultListForUsedTags = entityManager.createQuery(jpqlQueryForUsedTags)
                    .setParameter("userId", 1L)
                    .setParameter("pillId", 16L)
                    .getResultList();

            List<Object[]> resultListForNotUsedTags = entityManager.createQuery(jpqlQueryForNotUsedTags)
                    .setParameter("userId", 1L)
                    .setParameter("pillId", 16L)
                    .getResultList();

            List<TagDto> tempUsedTags = new ArrayList<>();
            List<TagDto> tempNotUsedTags = new ArrayList<>();

            for (Object[] result : resultListForUsedTags) {
                tempUsedTags.add(new TagDto((long) result[1], (long) result[2], (String) result[3]));
            }

            for (Object[] result : resultListForNotUsedTags) {
                tempNotUsedTags.add(new TagDto((long) result[1], (long) result[2], (String) result[3]));
            }

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
}

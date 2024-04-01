package com.mayak.chuckchuck.service;

import com.mayak.chuckchuck.domain.*;
import com.mayak.chuckchuck.dto.CategoryDto;
import com.mayak.chuckchuck.dto.PagingDto;
import com.mayak.chuckchuck.dto.PillDetailDto;
import com.mayak.chuckchuck.dto.TagDto;
import com.mayak.chuckchuck.dto.request.UserPillEffectListAndSearchRequest;
import com.mayak.chuckchuck.dto.request.UserPillEffectMemoRequest;
import com.mayak.chuckchuck.dto.request.UserPillEffectRegistInfoRequest;
import com.mayak.chuckchuck.dto.response.UserPillEffectListAndSearchResponse;
import com.mayak.chuckchuck.dto.response.UserPillEffectResponse;
import com.mayak.chuckchuck.dto.response.UserPillSideEffectListResponse;
import com.mayak.chuckchuck.repository.*;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * 약효기록 상세 조회 & 등록
     * @author 최진학
     * @param pillId (
     * @return userPillEffectList (사용자 약효 기록 내역)
     */
    public UserPillEffectResponse registUserPillEffect(User user, Long pillId) {
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
                                                "AND t.category.categoryId = :categoryId ";

            // 현재 약에서 사용하지 않는, 내가 작성했던 태그들
            String jpqlQueryForNotUsedTags = "SELECT t.category.categoryId, t.tagId, t.tagName " +
                                                "FROM Tag t" +
                                                " WHERE t.tagId NOT IN  (SELECT t.tagId " +
                                                                            "FROM UserPillEffect up " +
                                                                                "JOIN UserPillEffectToTag uptt ON up.userPillEffectId = uptt.userPillEffect.userPillEffectId " +
                                                                                "JOIN Tag t ON uptt.tag.tagId = t.tagId " +
                                                                            "WHERE up.user.userId = :userId " +
                                                                                "AND up.pill.pillId = :pillId " +
                                                                                "AND t.category.categoryId = :categoryId) " +
                                                    "AND t.user.userId = :userId " +
                                                    "AND t.category.categoryId = :categoryId ";

            // 카테고리별 사용중인 태그들
            List<TagDto> tempUsedTags = entityManager.createQuery(jpqlQueryForUsedTags, TagDto.class)
                    .setParameter("categoryId", categoryId)
                    .setParameter("userId", user.getUserId())
                    .setParameter("pillId", pillId)
                    .getResultList();

            // 카테고리별 사용중이지 않은 태그들
            List<TagDto> tempNotUsedTags = entityManager.createQuery(jpqlQueryForNotUsedTags, TagDto.class)
                    .setParameter("categoryId", categoryId)
                    .setParameter("userId", user.getUserId())
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
        UserPillEffect userPillEffect = userPillEffectRepository.findById(userPillEffectMemoRequest.userPillEffectId()).get();
        userPillEffect.updateMemo(userPillEffectMemoRequest.memo());
    }

    /**
     * 약효기록 - 추가 (업데이트) & 태그 등록 기능
     * @author 최진학
     * @param userPillEffectRegistInfoRequest (약효 기록 업데이트 request)
     * @return 없음
     */
    public void updateUserPillEffect(User user, UserPillEffectRegistInfoRequest userPillEffectRegistInfoRequest) {
        Long currentPillId = userPillEffectRegistInfoRequest.pillId();
        List<CategoryDto> categoryDtos = userPillEffectRegistInfoRequest.category();

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


    /**
     * 약효기록 - 문진표, 부작용 리스트 조회 (페이징 X)
     * @author 최진학
     * @param
     * @return 없음
     */
    public UserPillSideEffectListResponse getUserPillSideEffectList(User user) {
        List<UserPillEffect> userPillEffectList = userPillEffectRepository.findByUserAndCategory_CategoryIdOrderByCommonData_createDate(user, 1L);

        return UserPillSideEffectListResponse.fromEntity(userPillEffectList);
    }


    /**
     * 약효기록 - 전체 리스트 조회 & 검색
     * @author 최진학
     * @param userPillEffectListAndSearchRequest ()
     * @return 없음
     */
    public UserPillEffectListAndSearchResponse getUserPillEffectListAndSearch(User user, UserPillEffectListAndSearchRequest userPillEffectListAndSearchRequest) {
        Long categoryId = userPillEffectListAndSearchRequest.categoryId();
        String keyword = userPillEffectListAndSearchRequest.keyword();
        String page = userPillEffectListAndSearchRequest.page();

        // 값 담을 Dto list 생성
        List<PillDetailDto> totalPillDtoList = new ArrayList<>();
        List<PillDetailDto> siedEffectPillDtoList = new ArrayList<>();
        List<PillDetailDto> stopPillDtoList = new ArrayList<>();
        List<PillDetailDto> effectPillDtoList = new ArrayList<>();

        PagingDto pagingDto = new PagingDto(1, "createDate");

        // if : 키워드 X (전체 리스트 조회)
        // else : 키워드 O (검색)
        if (keyword == null) {
            List<UserPillEffect> userPillEffectList = userPillEffectRepository.findByUser(user, pagingDto.getPageable());

            for (UserPillEffect temp : userPillEffectList) {
                long currentCategoryId = temp.getCategory().getCategoryId();
                PillDetailDto currentPillDto = PillDetailDto.fromEntity(temp);

                // 전체 목록에는 항상 추가
                totalPillDtoList.add(currentPillDto);

                // 카테고리별 약 분류
                if (currentCategoryId == 1) siedEffectPillDtoList.add(currentPillDto);
                else if (currentCategoryId == 2) stopPillDtoList.add(currentPillDto);
                else if (currentCategoryId == 3) effectPillDtoList.add(currentPillDto);
            }
        } else {
            String jpqlQueryForPillList = "SELECT DISTINCT p " +
                    "FROM UserPillEffect upe " +
                    "JOIN upe.pill p " +
                    "WHERE upe.user.userId = :userId " +
                    "AND p.name LIKE CONCAT('%', :keyword, '%')";

            // if   : 전체 목록
            // else : 각 카테고리 별 목록
            List<Pill> tempPillList = entityManager.createQuery(jpqlQueryForPillList, Pill.class)
                    .setParameter("userId", 2L)
                    .setParameter("keyword", keyword)
                    .getResultList();
        
            // 전체 목록
            totalPillDtoList = PillDetailDto.fromEntity(tempPillList);
            
            // 카테고리별 목록
            jpqlQueryForPillList += "AND upe.category.categoryId = :categoryId ";

            Map<Integer, List<PillDetailDto>> pillMap = new HashMap<>();
            for (int i = 1; i <= 3; i++) {
                List<Pill> temp = entityManager.createQuery(jpqlQueryForPillList, Pill.class)
                        .setParameter("userId", 2L)
                        .setParameter("keyword", keyword)
                        .setParameter("categoryId", i)
                        .getResultList();

                List<PillDetailDto> pillDetailDtoList = PillDetailDto.fromEntity(temp);
                pillMap.put(i, pillDetailDtoList);
            }

            siedEffectPillDtoList = pillMap.get(1);
            stopPillDtoList = pillMap.get(2);
            effectPillDtoList = pillMap.get(3);


            System.out.println(totalPillDtoList);
            System.out.println(siedEffectPillDtoList);
            System.out.println(stopPillDtoList);
            System.out.println(effectPillDtoList);

        }

        return new UserPillEffectListAndSearchResponse(totalPillDtoList, siedEffectPillDtoList, stopPillDtoList, effectPillDtoList);
    }
}

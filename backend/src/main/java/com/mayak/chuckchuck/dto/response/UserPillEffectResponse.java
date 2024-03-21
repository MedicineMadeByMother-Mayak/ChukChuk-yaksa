package com.mayak.chuckchuck.dto.response;

import com.mayak.chuckchuck.domain.UserPillEffect;
import com.mayak.chuckchuck.dto.TagDto;
import com.mayak.chuckchuck.dto.UserPillEffectDto;

import java.util.List;

public record UserPillEffectResponse(List<UserPillEffectDto> userPillEffectDtos) {

    public static UserPillEffectResponse fromEntity(List<UserPillEffect> userPillEffectList, List<List<TagDto>> usedTags, List<List<TagDto>> notUsedTags) {
        List<UserPillEffectDto> userPillEffectDtos = userPillEffectList.stream()
                .map(userPillEffect -> new UserPillEffectDto(userPillEffect.getUserPillEffectId(),
                                                                userPillEffect.getCategory().getCategoryId(),
                                                                userPillEffect.getCategory().getCategoryName(),
                                                                usedTags.get({index}),  // 사용중인 태그 가져오기
                                                                notUsedTags.get({index}),  // 사용중인 태그 가져오기
                                                                userPillEffect.getMemo()))
                .toList();

        return new UserPillEffectResponse(userPillEffectDtos);
    }

    List<UserPillEffectDto> userPillEffectDtos = new ArrayList<>();
for (UserPillEffect userPillEffect : userPillEffectList) {
        int categoryId = (int) userPillEffect.getCategory().getCategoryId() - 1;
        List<TagDto> usedTagsForCategory = usedTags.get(categoryId);
        List<TagDto> notUsedTagsForCategory = notUsedTags.get(categoryId);
        userPillEffectDtos.add(new UserPillEffectDto(
                userPillEffect.getUserPillEffectId(),
                categoryId + 1, // 카테고리 인덱스는 0부터 시작하므로 1을 더해줍니다.
                userPillEffect.getCategory().getCategoryName(),
                usedTagsForCategory,
                notUsedTagsForCategory,
                userPillEffect.getMemo()
        ));
    }

}

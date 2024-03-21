package com.mayak.chuckchuck.dto.response;

import com.mayak.chuckchuck.domain.UserPillEffect;
import com.mayak.chuckchuck.dto.TagDto;
import com.mayak.chuckchuck.dto.UserPillEffectDto;

import java.util.ArrayList;
import java.util.List;

public record UserPillEffectResponse(List<UserPillEffectDto> userPillEffectDtos) {

    public static UserPillEffectResponse fromEntity(List<UserPillEffect> userPillEffectList, List<List<TagDto>> usedTags, List<List<TagDto>> notUsedTags) {
        List<UserPillEffectDto> userPillEffectDtos = new ArrayList<>();
        for (int i = 0; i < userPillEffectList.size(); i++) {
            UserPillEffect userPillEffect = userPillEffectList.get(i);
            List<TagDto> usedTagList = usedTags.get(i);
            List<TagDto> notUsedTagList = notUsedTags.get(i);

            userPillEffectDtos.add(new UserPillEffectDto(
                    userPillEffect.getUserPillEffectId(),
                    userPillEffect.getCategory().getCategoryId(),
                    userPillEffect.getCategory().getCategoryName(),
                    usedTagList,
                    notUsedTagList,
                    userPillEffect.getMemo()
            ));
        }

        return new UserPillEffectResponse(userPillEffectDtos);
    }
}

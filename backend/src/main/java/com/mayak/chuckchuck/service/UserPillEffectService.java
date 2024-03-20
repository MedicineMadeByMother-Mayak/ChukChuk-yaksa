package com.mayak.chuckchuck.service;

import com.mayak.chuckchuck.domain.UserPillEffect;
import com.mayak.chuckchuck.dto.request.UserPillEffectMemoRequest;
import com.mayak.chuckchuck.repository.UserPillEffectRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class UserPillEffectService {
    private final UserPillEffectRepository userPillEffectRepository;

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

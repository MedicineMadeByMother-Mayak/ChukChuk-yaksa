package com.mayak.chuckchuck.service;

import com.mayak.chuckchuck.domain.*;
import com.mayak.chuckchuck.dto.PagingDto;
import com.mayak.chuckchuck.dto.PrescriptionInfoDto;
import com.mayak.chuckchuck.dto.request.PillBagInfoRequest;
import com.mayak.chuckchuck.dto.response.PillBagResponse;
import com.mayak.chuckchuck.exception.ErrorCode.CommonErrorCode;
import com.mayak.chuckchuck.exception.RestApiException;
import com.mayak.chuckchuck.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class PillBagService {
    private final PillBagRepository pillBagRepository;
    private final OCRPillsRepository ocrPillsRepository;
    private final PillRepository pillRepository;
    private final TakeListRepository takeListRepository;
    private final TakePillsRepository takePillsRepository;

    /**
     * 약봉투 내역 저장
     * @author: 최서현
     * @param pillBagInfo
     * @return:
     */
    public void registPillBag(User user, PillBagInfoRequest pillBagInfo) {
        //약봉투 저장
        PillBag pillBag = PillBag.createPillBag(pillBagInfo, user);
        pillBagRepository.save(pillBag);

        //약봉투 내용 저장 및 복용리스트에 추가
        List<PrescriptionInfoDto> pills = pillBagInfo.pills();
        TakeList  takeList =TakeList.createTakeList(user);
        takeListRepository.save(takeList);

        for(PrescriptionInfoDto pillInfo : pills){
            Optional<Pill> pill = pillRepository.findById(pillInfo.pillId());
            if(!pill.isPresent()) throw new RestApiException(CommonErrorCode.INVALID_PARAMETER);

            //약봉투안의 약 저장
            OCRPills ocrPills = OCRPills.createOCRPills(pill.get(), pillBag, pillInfo.guide());
            ocrPillsRepository.save(ocrPills);

            //복용리스트에 약 추가
            TakePills takePills = TakePills.createTakePills(takeList, pill.get());
            takePillsRepository.save(takePills);
        }
    }

    /**
     * 약봉투내역 조회
     * @author: 김태완
     * @param:
     * @return: PillBagResponse
     */
    public PillBagResponse getPillBagResponse(User user, int page) {
        PagingDto pagingDto = new PagingDto(page, "buildDate");
        Page<PillBag> pillBags = pillBagRepository
                .findByUser(user, pagingDto.getPageable());
        List<PillBagResponse.PillBagDto> pillBagResult = pillBags.getContent().stream()
                .map(pillBag -> {
                    PillBagResponse.PillBagReceipt receipt = PillBagResponse.fromReceipt(pillBag);
                    List<PillBagResponse.OCRPillsGuide> guide = ocrPillsRepository.findByOcrListOcrId(pillBag.getOcrId()).stream()
                            .map(PillBagResponse::fromGuide).toList();

                    return new PillBagResponse.PillBagDto(receipt, guide);
                })
                .collect(Collectors.toList());
        return PillBagResponse.fromEntity((int) pillBags.getTotalElements(), pillBagResult);
    }
}

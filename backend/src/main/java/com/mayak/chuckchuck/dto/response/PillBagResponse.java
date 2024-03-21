package com.mayak.chuckchuck.dto.response;

import com.mayak.chuckchuck.domain.OCRPills;
import com.mayak.chuckchuck.domain.PillBag;

import java.time.LocalDateTime;
import java.util.List;

public record PillBagResponse(int count, List<PillBagDto> result) {
    public static PillBagReceipt fromReceipt(PillBag pillBags) {
        return new PillBagReceipt(
                pillBags.getPharmacyName(),
                pillBags.getBuildDate(),
                pillBags.getCost()
        );
    }

    public static OCRPillsGuide fromGuide(OCRPills ocrPills) {
        return new OCRPillsGuide(
                ocrPills.getPill().getName(),
                ocrPills.getPill().getType(),
                ocrPills.getPill().getCapacity()
        );
    }

    public static PillBagResponse fromEntity(int count,List<PillBagDto> pillBagDtos) {
        return new PillBagResponse(
                count,
                pillBagDtos
        );
    }
//    private final OCRPillsRepository ocrPillsRepository;
//    public static PillBagResponse fromEntity(Page<PillBag> pillBags) {
//        List<PillBagDto> result = pillBags.getContent().stream()
//                .map(pillBag -> {
//                    // Receipt 정보 생성
//                    PillBagReceipt receipt = new PillBagReceipt(
//                            pillBag.getPharmacyName(),
//                            pillBag.getBuildDate(),
//                            pillBag.getCost()
//                    );
//
//                    // OCRPillsGuide 정보 생성
//                    List<OCRPillsGuide> guide = ocrPillsRepository
//                            .findByOcrListOcrId(pillBag.getOcrId()).stream()
//                            .map(OCRPill ->
//                                    new OCRPillsGuide(
//                                            OCRPill.getPill().getName(),
//                                            OCRPill.getPill().getType(),
//                                            OCRPill.getPill().getCapacity()))
//                            .collect(Collectors.toList());
//
//                    return new PillBagDto(receipt, guide);
//                })
//                .collect(Collectors.toList());
//
//        return new PillBagResponse((int) pillBags.getTotalElements(), result);
//    }
//
    public record PillBagDto (PillBagReceipt receipt, List<OCRPillsGuide> guide) {}
    public record PillBagReceipt (String pharmacyName, LocalDateTime buildDate, int cost) {}
    public record OCRPillsGuide (String pillName, String type, String capacity) {}
}

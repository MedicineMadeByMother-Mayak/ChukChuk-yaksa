package com.mayak.chuckchuck.dto;

import com.mayak.chuckchuck.domain.TakePills;
import com.mayak.chuckchuck.dto.response.TakeListResponse;

import java.util.List;

public record TakeListPillInfo (Long pillId,
                                String name,
                                String imageUrl,
                                String type,
                                boolean warningPregnant,
                                boolean warningUseDate,
                                boolean warningElders,
                                boolean warningTogether)
{}

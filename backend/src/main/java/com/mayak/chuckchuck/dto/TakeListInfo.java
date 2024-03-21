package com.mayak.chuckchuck.dto;

import com.mayak.chuckchuck.domain.CommonData;
import com.mayak.chuckchuck.domain.TakePills;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record TakeListInfo (Long takeListId, String takeListName, CommonData commonData, LocalDateTime finishDate, Boolean isFinished){
}

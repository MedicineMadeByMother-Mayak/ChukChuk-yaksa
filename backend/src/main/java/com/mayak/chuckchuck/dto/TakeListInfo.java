package com.mayak.chuckchuck.dto;

import com.mayak.chuckchuck.domain.CommonData;

import java.time.LocalDateTime;

public record TakeListInfo (Long takeListId, String takeListName, CommonData commonData, LocalDateTime finishDate, Boolean isFinished){
}

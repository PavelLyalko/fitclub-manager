package ru.yandex.practicum.mapper.visit;

import ru.yandex.practicum.controller.requestResponse.visit.VisitResponse;
import ru.yandex.practicum.service.Dto.VisitDto;

public class VisitResponseMapper {
    public static VisitResponse mapToVisitResponse(VisitDto visitDto){
        VisitResponse visitResponse = new VisitResponse();
        visitResponse.setRemainingDays(visitDto.getRemainingDays());
        visitResponse.setVisitStart(visitDto.getStartVisit());
        visitResponse.setSuccess(true);
        return visitResponse;
    }
}

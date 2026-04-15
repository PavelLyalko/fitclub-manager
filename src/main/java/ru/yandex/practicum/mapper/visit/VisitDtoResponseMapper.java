package ru.yandex.practicum.mapper.visit;

import ru.yandex.practicum.controller.requestResponse.visit.VisitDtoResponse;
import ru.yandex.practicum.service.Dto.VisitDto;

public class VisitDtoResponseMapper {
    public static VisitDtoResponse toVisitDtoResponse(VisitDto visitDto) {
        VisitDtoResponse visitDtoResponse = new VisitDtoResponse();
        visitDtoResponse.setId(visitDto.getId());
        visitDtoResponse.setClientId(visitDto.getClientId());
        visitDtoResponse.setStartVisit(visitDto.getStartVisit());
        visitDtoResponse.setEndVisit(visitDto.getEndVisit());
        return visitDtoResponse;
    }
}

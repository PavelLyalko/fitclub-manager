package ru.yandex.practicum.mapper.visit;

import ru.yandex.practicum.entity.Visit;
import ru.yandex.practicum.service.Dto.VisitDto;

public class VisitDtoMapper {
    public static VisitDto toVisitDto(Visit visit) {
        VisitDto visitDto = new VisitDto();
        visitDto.setId(visit.getId());
        visitDto.setClientId(visit.getClientId());
        visitDto.setStartVisit(visit.getStartVisit());
        visitDto.setEndVisit(visit.getEndVisit());
        return visitDto;
    }
}

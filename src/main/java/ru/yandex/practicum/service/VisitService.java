package ru.yandex.practicum.service;

import ru.yandex.practicum.controller.requestResponse.visit.VisitRequest;
import ru.yandex.practicum.service.Dto.VisitDto;

public interface VisitService {
    VisitDto openVisit(VisitRequest visitRequest);

    void closeVisit(VisitRequest visitRequest);

    VisitDto getVisitByClientId(long clientId);
}

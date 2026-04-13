package ru.yandex.practicum.repository;

import ru.yandex.practicum.entity.Visit;

import java.time.LocalDateTime;

public interface VisitRepository {
    Visit openVisit(long clientId, LocalDateTime startVisit);
    int closeVisit(long clientId, LocalDateTime endVisit);

    Visit getVisitByClientId(long clientId);
}

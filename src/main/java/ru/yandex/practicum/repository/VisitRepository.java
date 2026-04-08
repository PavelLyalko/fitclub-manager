package ru.yandex.practicum.repository;

import java.time.LocalDateTime;

public interface VisitRepository {
    void openVisit(long clientId, LocalDateTime startVisit);
}

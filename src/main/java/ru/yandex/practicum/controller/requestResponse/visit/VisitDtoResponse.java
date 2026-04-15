package ru.yandex.practicum.controller.requestResponse.visit;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class VisitDtoResponse {
    private long id;
    private long clientId;
    private int remainingDays;
    private LocalDateTime startVisit;
    private LocalDateTime endVisit;
}

package ru.yandex.practicum.service.Dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class VisitDto {
    private long id;
    private long clientId;
    private int remainingDays;
    private LocalDateTime startVisit;
    private LocalDateTime endVisit;
}

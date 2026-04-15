package ru.yandex.practicum.controller.requestResponse.visit;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class VisitResponse {
    private int remainingDays;
    private boolean success;
    private LocalDateTime visitStart;
}

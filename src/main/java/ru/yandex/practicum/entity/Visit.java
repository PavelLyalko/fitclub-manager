package ru.yandex.practicum.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Visit {
    private long id;
    private long clientId;
    private LocalDateTime startVisit;
    private LocalDateTime endVisit;
}


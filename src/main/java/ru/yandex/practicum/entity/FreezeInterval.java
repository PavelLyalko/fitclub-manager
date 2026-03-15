package ru.yandex.practicum.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class FreezeInterval {
    private long id;
    private long membershipId;
    private LocalDate startDate;
    private LocalDate endDate;
}

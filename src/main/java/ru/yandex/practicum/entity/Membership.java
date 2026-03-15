package ru.yandex.practicum.entity;

import lombok.Data;
import ru.yandex.practicum.enums.MembershipStatus;
import ru.yandex.practicum.enums.MembershipType;

import java.time.LocalDate;

@Data
public class Membership {
    private long id;
    private long clientId;
    private MembershipType membershipType;
    private LocalDate startDate;
    private int totalDays;
    private MembershipStatus membershipStatus;
    private int totalFreezeDays;
}

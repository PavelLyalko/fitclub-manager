package ru.yandex.practicum.controller.requestResponse.membership;

import lombok.Data;
import ru.yandex.practicum.enums.MembershipStatus;
import ru.yandex.practicum.enums.MembershipType;

import java.time.LocalDate;

@Data
public class MembershipResponse {
    private long id;
    private long clientId;
    private MembershipType membershipType;
    private LocalDate startDate;
    private LocalDate endDate;
    private MembershipStatus membershipStatus;
    private int totalFreezeDays;
}

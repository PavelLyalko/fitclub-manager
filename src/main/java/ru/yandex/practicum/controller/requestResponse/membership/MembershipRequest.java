package ru.yandex.practicum.controller.requestResponse.membership;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ru.yandex.practicum.enums.MembershipType;

import java.time.LocalDate;

@Data
public class MembershipRequest {
    @NotNull
    private MembershipType membershipType;
    @NotNull
    private LocalDate startDate;
    @NotNull
    private LocalDate endDate;
    @NotNull
    private int totalFreezeDays;
}

package ru.yandex.practicum.controller.requestResponse.membership;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import ru.yandex.practicum.enums.MembershipType;

import java.time.LocalDate;

@Data
public class MembershipRequest {
    @NotNull
    private MembershipType membershipType;
    @NotNull
    private LocalDate startDate;
    @PositiveOrZero
    private int totalFreezeDays;
}

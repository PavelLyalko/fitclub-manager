package ru.yandex.practicum.controller.requestResponse.membership;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.yandex.practicum.enums.MembershipType;

import java.time.LocalDate;

@AllArgsConstructor
@Data
public class CreateMembershipRequest {
    @NotNull
    private MembershipType membershipType;
    @NotNull
    private LocalDate startDate;
    @PositiveOrZero
    private int totalFreezeDays;
}

package ru.yandex.practicum.servise;

import jakarta.validation.Valid;
import ru.yandex.practicum.servise.Dto.MembershipDto;

public interface MembershipService {
    MembershipDto createMembershipToClient(@Valid MembershipDto membershipDto);
}

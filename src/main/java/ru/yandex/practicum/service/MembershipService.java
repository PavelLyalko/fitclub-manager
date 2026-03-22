package ru.yandex.practicum.service;

import jakarta.validation.Valid;
import ru.yandex.practicum.service.Dto.MembershipDto;

public interface MembershipService {
    MembershipDto createMembershipToClient(@Valid MembershipDto membershipDto);
}

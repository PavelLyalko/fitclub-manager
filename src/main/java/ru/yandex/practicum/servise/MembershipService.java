package ru.yandex.practicum.servise;

import jakarta.validation.Valid;
import ru.yandex.practicum.servise.Dto.MembershipDto;

public interface MembershipService {
    MembershipDto createMembershipToClient(long clientId, @Valid MembershipDto membershipDto);
}

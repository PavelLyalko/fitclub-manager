package ru.yandex.practicum.service;

import jakarta.validation.Valid;
import ru.yandex.practicum.service.Dto.MembershipDto;

import java.util.List;

public interface MembershipService {
    MembershipDto createMembershipToClient(@Valid MembershipDto membershipDto);

    MembershipDto getMembershipToClient(long membershipId);

    void deleteMembershipToClient(long membershipId);

    List<MembershipDto> getMemberships();

    int getRemainingDays(long clientId);

    MembershipDto getActiveMembershipByClientId(long clientId);
}

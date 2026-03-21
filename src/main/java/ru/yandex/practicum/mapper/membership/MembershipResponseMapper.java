package ru.yandex.practicum.mapper.membership;

import ru.yandex.practicum.controller.requestResponse.membership.CreateMembershipResponse;
import ru.yandex.practicum.servise.Dto.MembershipDto;

public class MembershipResponseMapper {
    public static CreateMembershipResponse toMembershipResponse(MembershipDto membershipDto) {
        CreateMembershipResponse createMembershipResponse = new CreateMembershipResponse();
        createMembershipResponse.setId(membershipDto.getId());
        createMembershipResponse.setClientId(membershipDto.getClientId());
        createMembershipResponse.setMembershipType(membershipDto.getMembershipType());
        createMembershipResponse.setMembershipStatus(membershipDto.getMembershipStatus());
        createMembershipResponse.setStartDate(membershipDto.getStartDate());
        createMembershipResponse.setTotalDays(membershipDto.getTotalDays());
        createMembershipResponse.setTotalFreezeDays(membershipDto.getTotalFreezeDays());
        return createMembershipResponse;
    }
}

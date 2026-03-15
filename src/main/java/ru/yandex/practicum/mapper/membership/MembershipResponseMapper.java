package ru.yandex.practicum.mapper.membership;

import ru.yandex.practicum.controller.requestResponse.membership.MembershipResponse;
import ru.yandex.practicum.servise.Dto.MembershipDto;

public class MembershipResponseMapper {
    public static MembershipResponse toMembershipResponse(MembershipDto membershipDto) {
        MembershipResponse membershipResponse = new MembershipResponse();
        membershipResponse.setId(membershipDto.getId());
        membershipResponse.setClientId(membershipDto.getClientId());
        membershipResponse.setMembershipType(membershipDto.getMembershipType());
        membershipResponse.setMembershipStatus(membershipDto.getMembershipStatus());
        membershipResponse.setStartDate(membershipDto.getStartDate());
        membershipResponse.setEndDate(membershipDto.getEndDate());
        membershipResponse.setTotalFreezeDays(membershipDto.getTotalFreezeDays());
        return membershipResponse;
    }
}

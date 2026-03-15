package ru.yandex.practicum.mapper.membership;

import ru.yandex.practicum.controller.requestResponse.membership.MembershipRequest;
import ru.yandex.practicum.servise.Dto.MembershipDto;

public class MembershipRequestMapper {
    public static MembershipDto toMembershipDto(MembershipRequest membershipRequest) {
        MembershipDto membershipDto = new MembershipDto();
        membershipDto.setMembershipType(membershipRequest.getMembershipType());
        membershipDto.setStartDate(membershipRequest.getStartDate());
        membershipDto.setEndDate(membershipRequest.getEndDate());
        membershipDto.setTotalFreezeDays(membershipRequest.getTotalFreezeDays());
        return membershipDto;
    }
}

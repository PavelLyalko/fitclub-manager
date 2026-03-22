package ru.yandex.practicum.mapper.membership;

import ru.yandex.practicum.controller.requestResponse.membership.CreateMembershipRequest;
import ru.yandex.practicum.service.Dto.MembershipDto;

public class MembershipRequestMapper {
    public static MembershipDto toMembershipDto(CreateMembershipRequest createMembershipRequest) {
        MembershipDto membershipDto = new MembershipDto();
        membershipDto.setMembershipType(createMembershipRequest.getMembershipType());
        membershipDto.setStartDate(createMembershipRequest.getStartDate());
        membershipDto.setTotalFreezeDays(createMembershipRequest.getTotalFreezeDays());
        return membershipDto;
    }
}

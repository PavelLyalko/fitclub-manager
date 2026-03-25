package ru.yandex.practicum.mapper.membership;

import ru.yandex.practicum.entity.Membership;
import ru.yandex.practicum.service.Dto.MembershipDto;

public class MemberShipDtoMapper {
    public static Membership toMemberShip(MembershipDto dto) {
        Membership membership = new Membership();
        membership.setClientId(dto.getClientId());
        membership.setStartDate(dto.getStartDate());
        membership.setTotalDays(dto.getTotalDays());
        membership.setMembershipType(dto.getMembershipType());
        membership.setTotalFreezeDays(dto.getTotalFreezeDays());
        return membership;
    }

    public static MembershipDto toMembershipDto(Membership membership) {
        MembershipDto dto = new MembershipDto();
        dto.setId(membership.getId());
        dto.setClientId(membership.getClientId());
        dto.setMembershipStatus(membership.getMembershipStatus());
        dto.setStartDate(membership.getStartDate());
        dto.setTotalDays(membership.getTotalDays());
        dto.setMembershipType(membership.getMembershipType());
        dto.setTotalFreezeDays(membership.getTotalFreezeDays());
        return dto;
    }
}

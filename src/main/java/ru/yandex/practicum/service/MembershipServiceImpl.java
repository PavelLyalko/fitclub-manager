package ru.yandex.practicum.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.entity.Membership;
import ru.yandex.practicum.enums.MembershipStatus;
import ru.yandex.practicum.exception.ClientNotFoundException;
import ru.yandex.practicum.exception.DataInvalidException;
import ru.yandex.practicum.exception.MembershipNotFoundException;
import ru.yandex.practicum.mapper.membership.MemberShipDtoMapper;
import ru.yandex.practicum.repository.MembershipRepository;
import ru.yandex.practicum.service.Dto.MembershipDto;

import java.time.LocalDate;
import java.util.List;

@Service

@RequiredArgsConstructor
public class MembershipServiceImpl implements MembershipService {
    private final MembershipRepository membershipRepository;
    private final ClientService clientService;

    @Override
    public MembershipDto createMembershipToClient(MembershipDto membershipDto) {
        if (clientService.getClientById(membershipDto.getClientId()) == null) {
            throw new ClientNotFoundException(membershipDto.getClientId());
        }

        membershipDto.setTotalDays(membershipDto.getMembershipType().getDays());
        if (membershipDto.getStartDate().isBefore(LocalDate.now())) {
            throw new DataInvalidException("Дата начала действия абонемента не может быть в прошлом.");
        }
        Membership membership = MemberShipDtoMapper.toMemberShip(membershipDto);
        MembershipDto membershipDtoResponse = MemberShipDtoMapper.toMembershipDto(membershipRepository.createMembershipToClient(membership));
        updateMembershipStatus(membershipDtoResponse);

        return membershipDtoResponse;
    }

    @Override
    public MembershipDto getMembershipToClient(long membershipId) {
        MembershipDto membershipDtoResponse = MemberShipDtoMapper.toMembershipDto(membershipRepository.getMemberShipToClient(membershipId));
        updateMembershipStatus(membershipDtoResponse);
        return membershipDtoResponse;
    }

    @Override
    public void deleteMembershipToClient(long membershipId) {
        if (membershipRepository.getMemberShipToClient(membershipId) == null) {
            throw new MembershipNotFoundException("абонемента с таким id не найден:" + membershipId);
        }
        membershipRepository.deleteMembershipToClient(membershipId);
    }

    @Override
    public List<MembershipDto> getMemberships() {

        return membershipRepository.getMemberships().stream().map(MemberShipDtoMapper::toMembershipDto).peek(this::updateMembershipStatus).toList();
    }

    public void updateMembershipStatus(MembershipDto membershipDtoResponse) {
        if (membershipDtoResponse.getStartDate().isBefore(LocalDate.now())) {
            membershipDtoResponse.setMembershipStatus(MembershipStatus.INACTIVE);
        } else {
            membershipDtoResponse.setMembershipStatus(MembershipStatus.ACTIVE);
        }
    }
}
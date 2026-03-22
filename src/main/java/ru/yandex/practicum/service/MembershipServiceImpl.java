package ru.yandex.practicum.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.entity.Membership;
import ru.yandex.practicum.enums.MembershipStatus;
import ru.yandex.practicum.exception.ClientNotFoundException;
import ru.yandex.practicum.exception.DataInvalidException;
import ru.yandex.practicum.mapper.membership.MemberShipDtoMapper;
import ru.yandex.practicum.repository.MembershipRepository;
import ru.yandex.practicum.service.Dto.MembershipDto;

import java.time.DateTimeException;
import java.time.LocalDate;

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
        if (membershipDto.getStartDate().isBefore(LocalDate.now())){
            throw new DataInvalidException("Дата начала действия абонемента не может быть в прошлом.");
        }
        Membership membership = MemberShipDtoMapper.toMemberShip(membershipDto);
        MembershipDto membershipDtoResponse = MemberShipDtoMapper.toMembershipDto(membershipRepository.createMembershipToClient(membership));

        membershipDtoResponse.setMembershipStatus(MembershipStatus.ACTIVE);// тут надо высчитывать статус

        return membershipDtoResponse;
    }
}

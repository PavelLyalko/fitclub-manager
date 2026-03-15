package ru.yandex.practicum.servise;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.entity.Membership;
import ru.yandex.practicum.exception.ClientNotFoundException;
import ru.yandex.practicum.mapper.membership.MemberShipDtoMapper;
import ru.yandex.practicum.repository.MembershipRepository;
import ru.yandex.practicum.servise.Dto.MembershipDto;


@Service
public class MembershipServiceImpl implements MembershipService {
    private MembershipRepository membershipRepository;
    private ClientService clientService;
    @Override
    public MembershipDto createMembershipToClient(MembershipDto membershipDto) {
        if (clientService.getClientById(membershipDto.getClientId()) == null) {
            throw new ClientNotFoundException(membershipDto.getClientId());
        }

        membershipDto.setTotalDays(membershipDto.getMembershipType().getDays());
        Membership membership = MemberShipDtoMapper.toMemberShip(membershipDto);
        MembershipDto membershipDtoResponse = MemberShipDtoMapper.toMembershipDto(membershipRepository.createMembershipToClient(membership));
        membershipDtoResponse.setMembershipStatus();
        return
    }
}

package ru.yandex.practicum.servise;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.entity.Membership;
import ru.yandex.practicum.enums.MembershipStatus;
import ru.yandex.practicum.exception.ClientNotFoundException;
import ru.yandex.practicum.mapper.membership.MemberShipDtoMapper;
import ru.yandex.practicum.repository.MembershipRepository;
import ru.yandex.practicum.servise.Dto.MembershipDto;

@Service
public class MembershipServiceImpl implements MembershipService {
    private MembershipRepository membershipRepository;
    private ClientService clientService;
    @Override
    public MembershipDto createMembershipToClient(long clientId, MembershipDto membershipDto) {
        if (clientService.getClientById(clientId) == null) {
            throw new ClientNotFoundException(clientId);
        }
        membershipDto.setClientId(clientId);
        membershipDto.setMembershipStatus(MembershipStatus.ACTIVE);
        Membership membership = MemberShipDtoMapper.toMemberShip(membershipDto);
        return MemberShipDtoMapper.toMembershipDto(membershipRepository.createMembershipToClient(membership));
    }
}

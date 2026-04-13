package ru.yandex.practicum.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.controller.requestResponse.visit.VisitRequest;
import ru.yandex.practicum.enums.MembershipStatus;
import ru.yandex.practicum.exception.MembershipNotFoundException;
import ru.yandex.practicum.mapper.visit.VisitDtoMapper;
import ru.yandex.practicum.repository.VisitRepository;
import ru.yandex.practicum.service.Dto.MembershipDto;
import ru.yandex.practicum.service.Dto.VisitDto;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class VisitServiceImpl implements VisitService {

    private final MembershipService membershipService;
    private final VisitRepository visitRepository;

    @Override
    public VisitDto openVisit(VisitRequest visitRequest) {
        Long clientId = validateClientId(visitRequest);

        MembershipDto membership = getActiveMembership(clientId);
        if (membership == null) {
            throw new MembershipNotFoundException("Ваш абонемент не найден");
        }

        LocalDateTime now = LocalDateTime.now();
        VisitDto visit = VisitDtoMapper.toVisitDto(visitRepository.openVisit(clientId, now));
        visit.setRemainingDays(membershipService.getRemainingDays(clientId));
        return visit;
    }

    @Override
    public void closeVisit(VisitRequest visitRequest) {
        Long clientId = validateClientId(visitRequest);

        MembershipDto membership = getActiveMembership(clientId);
        if (membership == null) {
            throw new MembershipNotFoundException("Ваш абонемент не найден");
        }

        LocalDateTime now = LocalDateTime.now();
        int rowsAffected = visitRepository.closeVisit(clientId, now);
        if (rowsAffected == 0) {
            throw new IllegalStateException("Невозможно закрыть визит: нет активного визита");
        }
    }

    @Override
    public VisitDto getVisitByClientId(long clientId) {
        return VisitDtoMapper.toVisitDto(visitRepository.getVisitByClientId(clientId));
    }

    private Long validateClientId(VisitRequest visitRequest) {
        if (visitRequest == null) {
            throw new IllegalArgumentException("Client ID must not be null");
        }
        return visitRequest.getClientId();
    }

    private MembershipDto getActiveMembership(Long clientId) {
        MembershipDto membership = membershipService.getActiveMembershipByClientId(clientId);
        if (membership != null && MembershipStatus.ACTIVE.equals(membership.getMembershipStatus())) {
            return membership;
        }
        return null;
    }
}
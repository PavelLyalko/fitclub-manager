package ru.yandex.practicum.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.enums.MembershipStatus;
import ru.yandex.practicum.repository.VisitRepository;
import ru.yandex.practicum.service.Dto.MembershipDto;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class VisitServiceImpl implements VisitService {
    private final MembershipService membershipService;
    private final VisitRepository visitRepository;

    @Override
    public int openVisit(long clientId) {
        MembershipDto membership = membershipService.getActiveMembershipByClientId(clientId);
        if (membership != null && membership.getMembershipStatus() == (MembershipStatus.ACTIVE)) {
            LocalDateTime now = LocalDateTime.now();
            visitRepository.openVisit(clientId, now);
        }
        return membershipService.getRemainingDays(clientId);
    }
}
package ru.yandex.practicum.repository;

import ru.yandex.practicum.entity.Membership;

import java.util.List;

public interface MembershipRepository {
    Membership createMembershipToClient(Membership membership);

    Membership getActiveMembershipByClientId(long clientId);
    Membership getMemberShipToClient(long membershipId);

    void deleteMembershipToClient(long membershipId);

    List<Membership> getMemberships();
}

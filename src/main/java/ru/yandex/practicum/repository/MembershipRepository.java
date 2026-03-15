package ru.yandex.practicum.repository;

import ru.yandex.practicum.entity.Membership;

public interface MembershipRepository {
    Membership createMembershipToClient(Membership membership);
}

package ru.yandex.practicum.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.entity.Membership;
import ru.yandex.practicum.enums.MembershipType;

@RequiredArgsConstructor
@Repository
public class MembershipRepositoryImpl implements MembershipRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Membership createMembershipToClient(Membership membership) {
        Long typeId = getMembershipTypeId(membership.getMembershipType());

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("clientId", membership.getClientId());
        params.addValue("typeId", typeId);
        params.addValue("startDate", membership.getStartDate());
        params.addValue("totalDays", membership.getTotalDays());
        params.addValue("totalFreezeDays", membership.getTotalFreezeDays());// в бд отдельная таблица которая хранит дату начала и окончания заморозки

        Long id = namedParameterJdbcTemplate.queryForObject(MembershipQueries.INSERT_INTO_MEMBERSHIP, params, Long.class);
        if (id == null) {
            throw new RuntimeException("Insert into membership failed");
        }
        membership.setId(id);
        return membership;
    }

    private Long getMembershipTypeId(MembershipType membershipType) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", membershipType.toString());

        try {
            return namedParameterJdbcTemplate.queryForObject(MembershipQueries.SELECT_ID_FROM_MEMBERSHIP, params, Long.class);
        } catch (EmptyResultDataAccessException e) {
            throw new IllegalArgumentException(
                    ("MembershipType не найден: " + membershipType), e);
        }
    }
}

package ru.yandex.practicum.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.entity.Membership;
import ru.yandex.practicum.mapper.membership.MembershipRawMapper;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class MembershipRepositoryImpl implements MembershipRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Membership createMembershipToClient(Membership membership) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("clientId", membership.getClientId());
        params.addValue("membershipName", membership.getMembershipType().name());
        params.addValue("startDate", membership.getStartDate());
        params.addValue("totalDays", membership.getTotalDays());
        params.addValue("totalFreezeDays", membership.getTotalFreezeDays());

        Long id = namedParameterJdbcTemplate.queryForObject(MembershipQueries.INSERT_INTO_MEMBERSHIP, params, Long.class);
        if (id == null) {
            throw new RuntimeException("Insert into membership failed");
        }
        membership.setId(id);
        return membership;
    }

    @Override
    public Membership getMemberShipToClient(long membershipId) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", membershipId);
        return namedParameterJdbcTemplate.queryForObject(MembershipQueries.SELECT_MEMBERSHIP_WHERE_ID, params, new MembershipRawMapper());
    }

    @Override
    public void deleteMembershipToClient(long membershipId) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", membershipId);
        namedParameterJdbcTemplate.update(MembershipQueries.DELETE_MEMBERSHIP ,params);
    }

    @Override
    public List<Membership> getMemberships() {
        return namedParameterJdbcTemplate.query(MembershipQueries.SELECT_ALL_MEMBERSHIPS, new MembershipRawMapper());
    }
}

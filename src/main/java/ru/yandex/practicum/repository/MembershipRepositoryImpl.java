package ru.yandex.practicum.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.entity.Membership;
import ru.yandex.practicum.mapper.membership.MembershipRowMapper;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class MembershipRepositoryImpl implements MembershipRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Membership createMembershipToClient(Membership membership) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(MembershipQueries.INSERT_INTO_MEMBERSHIP, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, membership.getClientId());
            ps.setString(2, membership.getMembershipType().name());
            ps.setDate(3, java.sql.Date.valueOf(membership.getStartDate()));
            ps.setInt(4, membership.getTotalDays());
            ps.setInt(5, membership.getTotalFreezeDays());
            return ps;
        }, keyHolder);

        Long id = keyHolder.getKey().longValue();
        membership.setId(id);
        return membership;
    }

    @Override
    public Membership getActiveMembershipByClientId(long clientId) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("clientId", clientId);
        return namedParameterJdbcTemplate.queryForObject(MembershipQueries.SELECT_MEMBERSHIP_BY_CLIENT_ID, params, new MembershipRowMapper());
    }
    //TODO getActiveMembershipByClientId - реализовать метод


    @Override
    public Membership getMemberShipToClient(long membershipId) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", membershipId);
        return namedParameterJdbcTemplate.queryForObject(MembershipQueries.SELECT_MEMBERSHIP_WHERE_ID, params, new MembershipRowMapper());
    }

    @Override
    public void deleteMembershipToClient(long membershipId) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", membershipId);
        namedParameterJdbcTemplate.update(MembershipQueries.DELETE_MEMBERSHIP ,params);
    }

    @Override
    public List<Membership> getMemberships() {
        return namedParameterJdbcTemplate.query(MembershipQueries.SELECT_ALL_MEMBERSHIPS, new MembershipRowMapper());
    }
}

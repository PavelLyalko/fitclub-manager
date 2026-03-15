package ru.yandex.practicum.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.entity.Membership;

import java.util.HashMap;
import java.util.Map;

@Repository
public class MembershipRepositoryImpl implements MembershipRepository {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public MembershipRepositoryImpl(JdbcTemplate jdbcTemplate, SimpleJdbcInsert simpleJdbcInsert) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = simpleJdbcInsert;
    }

    @Override
    public Membership createMembershipToClient(Membership membership) {
        Map<String, Object> params = new HashMap<>();
        params.put("client_id", membership.getClientId());
        params.put("type_id", membership.getMembershipType());
        params.put("start_date", membership.getStartDate());
        params.put("total_days", membership.getTotalDays());
        params.put("total_freeze_days", membership.getTotalFreezeDays());

        Number key = simpleJdbcInsert.executeAndReturnKey(params);
        membership.setId(key.intValue());
        return membership;
    }
}

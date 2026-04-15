package ru.yandex.practicum.mapper.membership;

import org.springframework.jdbc.core.RowMapper;
import ru.yandex.practicum.entity.Membership;
import ru.yandex.practicum.enums.MembershipType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class MembershipRowMapper implements RowMapper<Membership> {
    public Membership mapRow(ResultSet rs, int rowNum) throws SQLException {
        Membership membership = new Membership();
        membership.setId(rs.getLong("id"));
        membership.setClientId(rs.getLong("client_id"));
        membership.setMembershipType(MembershipType.valueOf(rs.getString("membership_type")));
        membership.setStartDate(rs.getObject("start_date", LocalDate.class));
        membership.setTotalDays(rs.getInt("total_days"));
        membership.setTotalFreezeDays(rs.getInt("total_freeze_days"));
        return membership;
    }
}

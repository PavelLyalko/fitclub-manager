package ru.yandex.practicum.mapper.visit;

import org.springframework.jdbc.core.RowMapper;
import ru.yandex.practicum.entity.Visit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class VisitRowMapper implements RowMapper<Visit> {
    @Override
    public Visit mapRow(ResultSet rs, int rowNum) throws SQLException {
        Visit visit = new Visit();
        visit.setId(rs.getInt("id"));
        visit.setClientId(rs.getInt("client_id"));
        visit.setStartVisit(rs.getObject("start_visit", LocalDateTime.class));
        visit.setStartVisit(rs.getObject("end_visit", LocalDateTime.class));
        return visit;
    }
}

package ru.yandex.practicum.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.entity.Visit;
import ru.yandex.practicum.mapper.visit.VisitRowMapper;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Repository
public class VisitRepositoryImpl implements VisitRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public Visit openVisit(long clientId, LocalDateTime startVisit) {
         MapSqlParameterSource params = new MapSqlParameterSource();
         params.addValue("clientId", clientId);
         params.addValue("startVisit", startVisit);
         return jdbcTemplate.queryForObject(VisitQueries.INSERT_OPEN_VISIT, params, new VisitRowMapper());
    }

    @Override
    public int closeVisit(long clientId, LocalDateTime endVisit) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("clientId", clientId);
        params.addValue("endVisit", endVisit);
        return jdbcTemplate.update(VisitQueries.UPDATE_CLOSE_VISIT, params);
    }

    @Override
    public Visit getVisitByClientId(long clientId) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("clientId", clientId);
        return jdbcTemplate.queryForObject(VisitQueries.SELECT_VISIT_WHERE_ID, params, new VisitRowMapper());
    }
}

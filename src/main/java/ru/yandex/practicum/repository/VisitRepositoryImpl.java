package ru.yandex.practicum.repository;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;


import java.time.LocalDateTime;

@Repository
public class VisitRepositoryImpl implements VisitRepository {
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public void openVisit(long clientId, LocalDateTime startVisit) {

    }
}

package ru.yandex.practicum.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

@Component
public class ClientSimpleJdbcInsert extends SimpleJdbcInsert {

    public ClientSimpleJdbcInsert(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
        withTableName("client");
        usingGeneratedKeyColumns("id");
    }
}

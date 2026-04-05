package ru.yandex.practicum.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.entity.MyUser;
import ru.yandex.practicum.mapper.MyUser.MyUserRowMapper;

import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MyUserRepositoryImpl implements MyUserRepository {
    private static final String SQL = "SELECT u.id, u.username, u.password, r.name AS role " +
            "FROM users u " +
            "JOIN role r ON u.role_id = r.id " +
            "WHERE u.username = :username";

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public Optional<MyUser> findByUsername(String username) {
        System.out.println(username);
        Map<String, Object> params = Map.of("username", username);

        var xxx = jdbcTemplate.queryForObject(SQL, params, new MyUserRowMapper());
        System.out.println(xxx);

        return Optional.ofNullable(xxx);
    }
}

package ru.yandex.practicum.repository;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.entity.Client;
import ru.yandex.practicum.exception.ClientInsertException;
import ru.yandex.practicum.exception.ClientNotFoundException;
import ru.yandex.practicum.mapper.ClientRowMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ClientRepositoryImpl implements ClientRepository {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public ClientRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(this.jdbcTemplate)
                .withTableName("client")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public Client addClient(Client client) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", client.getName());
        params.put("email", client.getEmail());
        params.put("phone", client.getPhone());
        params.put("birth_date", client.getBirthday());

        Number key = simpleJdbcInsert.executeAndReturnKey(params);
        if (key == null) {
            throw new ClientInsertException("ошибка генерации ID клиента");
        }
        client.setId(key.longValue());
        return client;
    }

    @Override
    public Client getClientById(long clientId) {
        try {
            return jdbcTemplate.queryForObject(ClientQueries.SELECT_CLIENT_BY_ID, new Object[]{clientId}, new ClientRowMapper());
        } catch (EmptyResultDataAccessException e) {
            throw new ClientNotFoundException(clientId);
        }
    }

    @Override
    public List<Client> getAllClients() {
        return jdbcTemplate.query(ClientQueries.SELECT_ALL_CLIENTS, new ClientRowMapper());
    }

    @Override
    public void deleteClientById(long clientId) {

        jdbcTemplate.update(ClientQueries.DELETE_CLIENT_BY_ID, clientId);
    }
}

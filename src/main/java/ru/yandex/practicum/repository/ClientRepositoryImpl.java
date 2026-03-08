package ru.yandex.practicum.repository;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.entity.Client;
import ru.yandex.practicum.exception.ClientInsertException;
import ru.yandex.practicum.exception.ClientNotFoundException;
import ru.yandex.practicum.mapper.ClientRawMapper;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;


@Repository
public class ClientRepositoryImpl implements ClientRepository {
    private final JdbcTemplate jdbcTemplate;

    public ClientRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Client addClient(Client client) {
        String sql = "INSERT INTO client (name, phone, email, birth_date) VALUES (?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, client.getName());
            ps.setString(2, client.getPhone());
            ps.setString(3, client.getEmail());
            ps.setDate(4, Date.valueOf(client.getBirthday()));
            return ps;
        }, keyHolder);

        Number key = keyHolder.getKey();
        if (key == null) {
            throw new ClientInsertException("ошибка генерации id клиента");
        }
        long id = key.longValue();
        client.setId(id);
        return client;
    }

    @Override
    public Client getClientById(long clientId) {
        String sql = "SELECT * FROM client WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{clientId}, new ClientRawMapper());
        } catch (EmptyResultDataAccessException e) {
            throw new ClientNotFoundException(clientId);
        }
    }

    @Override
    public List<Client> getAllClients() {
        String sql = "SELECT * FROM client";
        return jdbcTemplate.query(sql, new ClientRawMapper());
    }

    @Override
    public void deleteClientById(long clientId) {
        String sql = "DELETE FROM client WHERE id = ?";
        jdbcTemplate.update(sql, clientId);//слышно?
    }
}

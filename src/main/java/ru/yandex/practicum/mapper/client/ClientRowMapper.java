package ru.yandex.practicum.mapper.client;

import org.springframework.jdbc.core.RowMapper;
import ru.yandex.practicum.entity.Client;

import java.sql.ResultSet;
import java.sql.SQLException;

public  class ClientRowMapper implements RowMapper<Client> {
    @Override
    public Client mapRow(ResultSet rs, int rowNum) throws SQLException {
        Client client = new Client();
        client.setId(rs.getLong("id"));
        client.setName(rs.getString("name"));
        client.setPhone(rs.getString("phone"));
        client.setEmail(rs.getString("email"));
        client.setBirthday(rs.getDate("birth_date") != null ? rs.getDate("birth_date").toLocalDate() : null);
        return client;
    }
}

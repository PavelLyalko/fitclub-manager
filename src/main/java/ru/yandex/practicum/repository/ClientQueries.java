package ru.yandex.practicum.repository;

public interface ClientQueries {
    String INSERT_CLIENT = "INSERT INTO client (name, phone, email, birth_date) VALUES (?, ?, ?, ?)";
    String SELECT_CLIENT_BY_ID = "SELECT name, phone, email, birth_date FROM client WHERE id = ?";
    String SELECT_ALL_CLIENTS = "SELECT name, phone, email, birth_date FROM client";
    String DELETE_CLIENT_BY_ID = "DELETE FROM client WHERE id = ?";
}

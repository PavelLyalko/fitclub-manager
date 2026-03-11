package ru.yandex.practicum.repository;

import ru.yandex.practicum.entity.Client;

import java.util.List;

public interface ClientRepository {
    Client addClient(Client client);

    Client getClientById(long clientId);

    List<Client> getAllClients();

    void deleteClientById(long clientId);
}


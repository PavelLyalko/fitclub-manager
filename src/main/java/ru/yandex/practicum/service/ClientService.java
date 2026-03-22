package ru.yandex.practicum.service;

import ru.yandex.practicum.service.Dto.ClientDto;

import java.util.List;

public interface ClientService {
    ClientDto addClient(ClientDto clientDto);

    ClientDto getClientById(long clientId);

    List<ClientDto> getAllClients();

    void deleteClientById(long clientId);
}

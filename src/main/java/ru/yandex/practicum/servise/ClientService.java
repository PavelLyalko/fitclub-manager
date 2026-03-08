package ru.yandex.practicum.servise;

import ru.yandex.practicum.controller.requestResponse.ClientResponse;
import ru.yandex.practicum.servise.Dto.ClientDto;

import java.util.List;

public interface ClientService {
    ClientDto addClient(ClientDto clientDto);

    ClientDto getClientById(long clientId);

    List<ClientDto> getAllClients();

    void deleteClientById(long clientId);
}

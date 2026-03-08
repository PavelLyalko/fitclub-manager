package ru.yandex.practicum.mapper;

import ru.yandex.practicum.entity.Client;
import ru.yandex.practicum.servise.Dto.ClientDto;

public class ClientDtoMapper {
    public static ClientDto toDto(Client client) {
        ClientDto clientDto = new ClientDto();
        clientDto.setId(client.getId());
        clientDto.setName(client.getName());
        clientDto.setEmail(client.getEmail());
        clientDto.setPhone(client.getPhone());
        clientDto.setBirthday(client.getBirthday());
        return clientDto;
    }

    public static Client toClient(ClientDto clientDto) {
        Client client = new Client();
        client.setId(clientDto.getId());
        client.setName(clientDto.getName());
        client.setEmail(clientDto.getEmail());
        client.setPhone(clientDto.getPhone());
        client.setBirthday(clientDto.getBirthday());
        return client;
    }
}

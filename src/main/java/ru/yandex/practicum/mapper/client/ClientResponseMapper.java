package ru.yandex.practicum.mapper.client;

import ru.yandex.practicum.controller.requestResponse.client.ClientResponse;
import ru.yandex.practicum.servise.Dto.ClientDto;

public class ClientResponseMapper {
    public static ClientResponse toClientResponse(ClientDto clientDto) {
        ClientResponse clientResponse = new ClientResponse();
        clientResponse.setId(clientDto.getId());
        clientResponse.setName(clientDto.getName());
        clientResponse.setEmail(clientDto.getEmail());
        clientResponse.setPhone(clientDto.getPhone());
        clientResponse.setBirthday(clientDto.getBirthday());
        return clientResponse;
    }
}

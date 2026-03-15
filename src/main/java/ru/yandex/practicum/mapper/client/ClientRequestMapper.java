package ru.yandex.practicum.mapper.client;

import ru.yandex.practicum.controller.requestResponse.client.ClientRequest;
import ru.yandex.practicum.servise.Dto.ClientDto;

public class ClientRequestMapper {
    public static ClientDto toDto(ClientRequest clientRequest) {
        ClientDto clientDto = new ClientDto();
        clientDto.setBirthday(clientRequest.getBirthday());
        clientDto.setEmail(clientRequest.getEmail());
        clientDto.setName(clientRequest.getName());
        clientDto.setPhone(clientRequest.getPhone());
        return clientDto;
    }
}

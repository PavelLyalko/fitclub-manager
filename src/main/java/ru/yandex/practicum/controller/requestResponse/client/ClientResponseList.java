package ru.yandex.practicum.controller.requestResponse.client;

import lombok.Data;

import java.util.List;

@Data
public class ClientResponseList {
    private List<ClientResponse> clients;

    public ClientResponseList(List<ClientResponse> clients) {
        this.clients = clients;
    }
}

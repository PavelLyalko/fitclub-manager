package ru.yandex.practicum.controller.requestResponse;

import lombok.Data;

import java.util.List;

@Data
public class ClientResponseList {
    private List<ClientResponse> clients;

    public ClientResponseList(List<ClientResponse> clients) {
        this.clients = clients;
    }
}

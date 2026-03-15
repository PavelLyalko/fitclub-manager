package ru.yandex.practicum.controller.requestResponse.client;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ClientResponse {
    private long id;
    private String name;
    private String phone;
    private String email;
    private LocalDate birthday;
}

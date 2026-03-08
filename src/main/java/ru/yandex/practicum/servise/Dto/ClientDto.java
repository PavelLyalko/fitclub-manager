package ru.yandex.practicum.servise.Dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ClientDto {
    private long id;
    private String name;
    private String phone;
    private String email;
    private LocalDate birthday;
}

package ru.yandex.practicum.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Client {
    private long id;
    private String name;
    private String phone;
    private String email;
    private LocalDate birthday;
}


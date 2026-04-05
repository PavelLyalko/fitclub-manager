package ru.yandex.practicum.entity;

import lombok.Data;

@Data
public class MyUser {
    private long id;
    private String username;
    private String password;
    private String role;
}

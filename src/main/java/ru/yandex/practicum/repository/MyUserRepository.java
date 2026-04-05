package ru.yandex.practicum.repository;

import ru.yandex.practicum.entity.MyUser;

import java.util.Optional;

public interface MyUserRepository {
    Optional<MyUser> findByUsername(String username);
}

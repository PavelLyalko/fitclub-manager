package ru.yandex.practicum.controller.requestResponse.client;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ClientRequest {
    @NotNull
    private String name;
    @NotNull
    private String phone;
    @NotNull
    private String email;
    @NotNull
    private LocalDate birthday;
}

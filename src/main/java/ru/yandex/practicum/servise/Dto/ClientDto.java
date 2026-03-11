package ru.yandex.practicum.servise.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {
    private long id;
    private String name;
    private String phone;
    private String email;
    private LocalDate birthday;
}

package ru.yandex.practicum.controller.requestResponse.visit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VisitRequest {
    private long clientId;
}

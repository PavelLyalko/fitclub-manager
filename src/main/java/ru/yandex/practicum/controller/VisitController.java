package ru.yandex.practicum.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.controller.requestResponse.visit.VisitDtoResponse;
import ru.yandex.practicum.controller.requestResponse.visit.VisitRequest;
import ru.yandex.practicum.controller.requestResponse.visit.VisitResponse;
import ru.yandex.practicum.mapper.visit.VisitDtoResponseMapper;
import ru.yandex.practicum.mapper.visit.VisitResponseMapper;
import ru.yandex.practicum.service.VisitService;

@RestController
@RequestMapping("/visits")
@RequiredArgsConstructor
public class VisitController {
    private final VisitService visitService;

    @PostMapping
    public VisitResponse openVisit(@RequestBody VisitRequest visitRequest) {
        return VisitResponseMapper.mapToVisitResponse(visitService.openVisit(visitRequest));
    }

    @PutMapping
    public void closeVisit(@RequestBody VisitRequest visitRequest) {
        visitService.closeVisit(visitRequest);
    }

    @GetMapping("/{clientId}")
    public VisitDtoResponse getVisitByClientId(@PathVariable long clientId) {
        return VisitDtoResponseMapper.toVisitDtoResponse(visitService.getVisitByClientId(clientId));
    }
}

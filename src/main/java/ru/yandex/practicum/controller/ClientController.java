package ru.yandex.practicum.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.controller.requestResponse.ClientRequest;
import ru.yandex.practicum.controller.requestResponse.ClientResponse;
import ru.yandex.practicum.mapper.ClientRequestMapper;
import ru.yandex.practicum.mapper.ClientResponseMapper;
import ru.yandex.practicum.servise.ClientService;
import ru.yandex.practicum.servise.Dto.ClientDto;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ClientResponse addClient(@Valid @RequestBody ClientRequest clientRequest) {
        ClientDto clientDto = ClientRequestMapper.toDto(clientRequest);
        return ClientResponseMapper.toClientResponse(clientService.addClient(clientDto));
    }

    @GetMapping("/{clientId}")
    public ClientResponse getClientById(@PathVariable long clientId) {
        return ClientResponseMapper.toClientResponse(clientService.getClientById(clientId));
    }

    @GetMapping
    public List<ClientResponse> getAllClients() {
        return clientService.getAllClients().stream().map(ClientResponseMapper::toClientResponse).toList();
    }

    @DeleteMapping("/{clientId}")
    public void deleteClientById(@PathVariable long clientId) {
        clientService.deleteClientById(clientId);
    }
}

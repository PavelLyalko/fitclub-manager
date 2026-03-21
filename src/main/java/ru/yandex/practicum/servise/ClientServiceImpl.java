package ru.yandex.practicum.servise;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.entity.Client;
import ru.yandex.practicum.mapper.client.ClientDtoMapper;
import ru.yandex.practicum.repository.ClientRepository;
import ru.yandex.practicum.servise.Dto.ClientDto;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    @Override
    public ClientDto addClient(ClientDto clientDto) {
        Client client = ClientDtoMapper.toClient(clientDto);
        return ClientDtoMapper.toDto(clientRepository.addClient(client));
    }

    @Override
    public ClientDto getClientById(long clientId) {
        return ClientDtoMapper.toDto(clientRepository.getClientById(clientId));
    }

    @Override
    public List<ClientDto> getAllClients() {
        return clientRepository.getAllClients().stream().map(ClientDtoMapper::toDto).toList();
    }

    @Override
    public void deleteClientById(long clientId) {
        clientRepository.deleteClientById(clientId);
    }
}

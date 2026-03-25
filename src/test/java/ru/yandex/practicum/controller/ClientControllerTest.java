package ru.yandex.practicum.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;
import ru.yandex.practicum.controller.requestResponse.client.ClientRequest;
import ru.yandex.practicum.service.ClientService;
import ru.yandex.practicum.service.Dto.ClientDto;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@AutoConfigureMockMvc
@SpringBootTest
public class ClientControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    ClientService clientService;

    @Test
    void saveNewClient_returnsClient() throws Exception {
        ClientRequest clientRequest = new ClientRequest();
        clientRequest.setName("Jon");
        clientRequest.setPhone("1234567890");
        clientRequest.setEmail("sdsd@mail.com");
        clientRequest.setBirthday(LocalDate.of(2000, 12, 12));

        mockMvc.perform(post("/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clientRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value("Jon"))
                .andExpect(jsonPath("$.phone").value("1234567890"))
                .andExpect(jsonPath("$.email").value("sdsd@mail.com"))
                .andExpect(jsonPath("$.birthday").value("2000-12-12"));
    }

    @Test
    void getClientById_returnsClient() throws Exception {
        ClientDto mockClientDto = new ClientDto(1L, "Jon", "1234567890", "user@mail.com", LocalDate.of(2000, 12, 12));

        clientService.addClient(mockClientDto);

        mockMvc.perform(get("/clients/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Jon"))
                .andExpect(jsonPath("$.phone").value("1234567890"))
                .andExpect(jsonPath("$.email").value("user@mail.com"))
                .andExpect(jsonPath("$.birthday").value("2000-12-12"));
    }

    @Test
    void getAllClients_returnsAllClients() throws Exception {

        ClientDto clientDto = new ClientDto(1L, "Jon", "1234567890", "user@mail.com", LocalDate.of(2000, 12, 12));
        ClientDto clientDto1 = new ClientDto(2L, "Bob", "1234564331", "client@mail.com", LocalDate.of(2000, 11, 11));
        clientService.addClient(clientDto);
        clientService.addClient(clientDto1);

        mockMvc.perform(get("/clients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.clients[0].id").value(1))
                .andExpect(jsonPath("$.clients[0].name").value("Jon"))
                .andExpect(jsonPath("$.clients[0].phone").value("1234567890"))
                .andExpect(jsonPath("$.clients[0].email").value("user@mail.com"))
                .andExpect(jsonPath("$.clients[0].birthday").value("2000-12-12"))
                .andExpect(jsonPath("$.clients[1].id").value(2))
                .andExpect(jsonPath("$.clients[1].name").value("Bob"))
                .andExpect(jsonPath("$.clients[1].phone").value("1234564331"))
                .andExpect(jsonPath("$.clients[1].email").value("client@mail.com"))
                .andExpect(jsonPath("$.clients[1].birthday").value("2000-11-11"));
    }

    @Test
    void deleteClient_returnsOk() throws Exception {
        clientService.deleteClientById(1L);

        mockMvc.perform(delete("/clients/1"))
                .andExpect(status().isOk());
    }
}

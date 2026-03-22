package ru.yandex.practicum.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import ru.yandex.practicum.controller.requestResponse.membership.CreateMembershipRequest;
import ru.yandex.practicum.enums.MembershipType;
import ru.yandex.practicum.service.MembershipService;

import java.time.LocalDate;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class MembershipControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private MembershipService membershipService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createMembershipToClientTestMustBeNotActive() throws Exception {
        LocalDate future = LocalDate.now().plusDays(1);

        CreateMembershipRequest request = new CreateMembershipRequest(MembershipType.ONE_MONTH, future, 31);
        long clientId = 2L;

        mockMvc.perform(post("/memberships/" + clientId)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.membershipType").value(request.getMembershipType().name()))
                .andExpect(jsonPath("$.startDate").value(request.getStartDate().toString()))
                .andExpect(jsonPath("$.totalFreezeDays").value(request.getTotalFreezeDays()))
                .andExpect(jsonPath("$.membershipStatus").value("ACTIVE"))
                .andExpect(jsonPath("$.clientId").value(clientId));
    }

    @Test
    void createMembershipToClientInPastMustBeValidated() throws Exception {
        LocalDate past = LocalDate.now().minusDays(1);
        CreateMembershipRequest request = new CreateMembershipRequest(MembershipType.ONE_MONTH, past, 31);
        long clientId = 2L;

        mockMvc.perform(post("/memberships/" + clientId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Дата начала действия абонемента не может быть в прошлом."));
    }
}

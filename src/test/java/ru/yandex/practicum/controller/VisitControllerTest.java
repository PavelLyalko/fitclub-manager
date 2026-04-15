package ru.yandex.practicum.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.yandex.practicum.controller.requestResponse.visit.VisitRequest;
import ru.yandex.practicum.entity.Membership;
import ru.yandex.practicum.entity.Visit;
import ru.yandex.practicum.repository.MembershipRepository;
import ru.yandex.practicum.repository.VisitRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class VisitControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private VisitRepository visitRepository;

    @MockBean
    private MembershipRepository membershipRepository;

    @Test
    void visitOpenTest() throws Exception {
        Long clientId = 1L;

        VisitRequest visitRequest = new VisitRequest(clientId);

        Membership activeMembership = new Membership();
        activeMembership.setId(1L);
        activeMembership.setClientId(clientId);
        activeMembership.setStartDate(LocalDate.now().minusDays(1));
        activeMembership.setTotalDays(30);

        when(membershipRepository.getActiveMembershipByClientId(clientId))
                .thenReturn(activeMembership);


        LocalDateTime now = LocalDateTime.now();

        Visit savedVisit = new Visit();
        savedVisit.setId(1L);
        savedVisit.setClientId(clientId);
        savedVisit.setStartVisit(now);

        when(visitRepository.openVisit(eq(clientId), any(LocalDateTime.class)))
                .thenReturn(savedVisit);

        mockMvc.perform(post("/visits")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(visitRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.visitStart").isString())
                .andExpect(jsonPath("$.remainingDays").value(29));
    }
}

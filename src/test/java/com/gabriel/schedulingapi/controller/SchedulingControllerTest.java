package com.gabriel.schedulingapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.gabriel.schedulingapi.business.SchedulingService;
import com.gabriel.schedulingapi.controller.dto.in.SchedulingRecordInput;
import com.gabriel.schedulingapi.controller.dto.out.SchedulingRecordOutput;
import com.gabriel.schedulingapi.infra.enums.NotificationStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class SchedulingControllerTest {

    @InjectMocks
    SchedulingController schedulingController;

    @Mock
    SchedulingService schedulingService;

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private SchedulingRecordInput schedulingRecordInput;
    private SchedulingRecordOutput schedulingRecordOutput;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(schedulingController).build();
        objectMapper.registerModule(new JavaTimeModule());

        schedulingRecordInput = new SchedulingRecordInput(
                "example@email.com",
                "99999999999",
                "This is a test",
                LocalDateTime.of(2025, 01, 02, 11, 01, 01));

        schedulingRecordOutput = new SchedulingRecordOutput(
                1L,
                "example@email.com",
                "99999999999",
                "This is a test",
                LocalDateTime.of(2025, 01, 02, 11, 01, 01),
                NotificationStatus.SCHEDULED);
    }

    @Test
    void shouldCreateSchedulingSuccessfully() throws Exception {
        when(schedulingService.create(schedulingRecordInput)).thenReturn(schedulingRecordOutput);

        mockMvc.perform(post("/api/v1/scheduling")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(schedulingRecordInput)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.recipientEmail").value("example@email.com"))
                .andExpect(jsonPath("$.recipientPhone").value(schedulingRecordOutput.recipientPhone()))
                .andExpect(jsonPath("$.message").value(schedulingRecordOutput.message()))
                .andExpect(jsonPath("$.sendDateTime").value("02-01-2025 11:01:01"))
                .andExpect(jsonPath("$.notificationStatus").value("SCHEDULED"));

        verify(schedulingService, times(1)).create(schedulingRecordInput);
    }
}

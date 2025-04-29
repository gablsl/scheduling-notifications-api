package com.gabriel.schedulingapi.business;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.gabriel.schedulingapi.business.mapper.ISchedulingMapper;
import com.gabriel.schedulingapi.controller.dto.in.SchedulingRecordInput;
import com.gabriel.schedulingapi.controller.dto.out.SchedulingRecordOutput;
import com.gabriel.schedulingapi.infra.entities.Scheduling;
import com.gabriel.schedulingapi.infra.enums.NotificationStatus;
import com.gabriel.schedulingapi.infra.repositories.SchedulingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SchedulingServiceTest {

    @InjectMocks
    private SchedulingService schedulingService;

    @Mock
    SchedulingRepository schedulingRepository;

    @Mock
    ISchedulingMapper schedulingMapper;

    private Scheduling schedulingEntity;
    private SchedulingRecordInput schedulingRecordInput;
    private SchedulingRecordOutput schedulingRecordOutput;

    @BeforeEach
    void setUp() {
        schedulingEntity = new Scheduling(1L,
                "example@email.com",
                "99999999999",
                LocalDateTime.of(2025, 1, 2, 11, 1, 1),
                LocalDateTime.now(),
                null,
                "This is a text",
                NotificationStatus.SCHEDULED);

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
        when(schedulingMapper.forEntity(schedulingRecordInput)).thenReturn(schedulingEntity);
        when(schedulingRepository.save(schedulingEntity)).thenReturn(schedulingEntity);
        when(schedulingMapper.forDto(schedulingEntity)).thenReturn(schedulingRecordOutput);

        SchedulingRecordOutput schedulingRecordOutput = schedulingService.create(schedulingRecordInput);

        verify(schedulingMapper, times(1)).forEntity(schedulingRecordInput);
        verify(schedulingRepository, times(1)).save(schedulingEntity);
        verify(schedulingMapper, times(1)).forDto(schedulingEntity);
        assertThat(schedulingRecordOutput).usingRecursiveAssertion().isEqualTo(schedulingRecordOutput);
    }
}

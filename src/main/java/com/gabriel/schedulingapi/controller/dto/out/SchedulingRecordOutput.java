package com.gabriel.schedulingapi.controller.dto.out;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gabriel.schedulingapi.infra.enums.NotificationStatus;

import java.time.LocalDateTime;

public record SchedulingRecordOutput(
        Long id,
        String recipientEmail,
        String recipientPhone,
        String message,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
        LocalDateTime sendDateTime,
        NotificationStatus notificationStatus
) {
}

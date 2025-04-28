package com.gabriel.schedulingapi.controller.dto.in;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record SchedulingRecordInput(
        String recipientEmail,
        String recipientPhone,
        String message,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
        LocalDateTime sendDateTime
) {
}

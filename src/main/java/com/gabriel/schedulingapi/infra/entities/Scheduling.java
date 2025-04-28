package com.gabriel.schedulingapi.infra.entities;

import com.gabriel.schedulingapi.infra.enums.NotificationStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "scheduling")
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Scheduling {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String recipientEmail;
    private String recipientPhone;
    private LocalDateTime sendDateTime;
    private LocalDateTime schedulingDateTime;
    private LocalDateTime modificationDateTime;
    private String message;
    private NotificationStatus notificationStatus;

    @PrePersist
    private void prePersist() {
        schedulingDateTime = LocalDateTime.now();
        notificationStatus = NotificationStatus.SCHEDULED;
    }
}

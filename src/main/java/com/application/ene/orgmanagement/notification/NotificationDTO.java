package com.application.ene.orgmanagement.notification;

import java.time.LocalDate;


public record NotificationDTO(
        LocalDate date,
        String format,
        String productName
) {
}
package com.application.ene.orgmanagement.notification.internal;

import java.time.LocalDate;

public record Notification(LocalDate date, NotificationType format, String productName) {
}

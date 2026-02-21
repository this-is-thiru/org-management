package com.application.ene.orgmanagement.notification;

import com.application.ene.orgmanagement.notification.internal.Notification;
import com.application.ene.orgmanagement.notification.internal.NotificationType;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.event.EventListener;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class NotificationService {

    public void createNotification(NotificationDTO notification) {
        log.info("Received notification by module dependency for product {} in date {} by {}.",
                notification.productName(),
                notification.date(),
                notification.format());
    }

    @ApplicationModuleListener
    public void notificationEvent(NotificationDTO event) {
        Notification notification = toEntity(event);
        log.info("Received notification by event for product {} in date {} by {}.",
                notification.productName(),
                notification.date(),
                notification.format());
    }

    @ApplicationModuleListener
    public void notificationEvent(String str) {
        log.info(str);
    }

    private Notification toEntity(NotificationDTO event) {
        return new Notification(event.date(), NotificationType.valueOf(event.format()), event.productName());
    }
}
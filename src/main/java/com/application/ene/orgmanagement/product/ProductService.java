package com.application.ene.orgmanagement.product;

import com.application.ene.orgmanagement.notification.NotificationDTO;
import com.application.ene.orgmanagement.notification.NotificationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final NotificationService notificationService;
    private final ApplicationEventPublisher events;

    @Transactional
    @Scheduled(cron = "*/1 * * * * *")
    public void create() {
        System.out.println("Hii");
        for (int i = 0; i < 10; i++) {
            events.publishEvent(new NotificationDTO(LocalDate.now(), "SMS", LocalDateTime.now().toString()));
        }

//        for (int i = 0; i < 10; i++) {
//            events.publishEvent("Giii");
//        }
    }

}
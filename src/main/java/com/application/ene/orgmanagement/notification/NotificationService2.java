package com.application.ene.orgmanagement.notification;

import com.application.ene.orgmanagement.product.ProductDTO;
import com.application.ene.orgmanagement.product.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class NotificationService2 {

    private final ProductService productService;

//    public void createNotification(NotificationDTO notification) {
//        productService.create(new ProductDTO("Hii", "Hello", 2));
//    }
}
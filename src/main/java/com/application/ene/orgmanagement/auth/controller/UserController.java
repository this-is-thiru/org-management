package com.application.ene.orgmanagement.auth.controller;

import com.application.ene.orgmanagement.auth.entity.UserDetail;
import com.application.ene.orgmanagement.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user-service")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/details/{customerId}")
    public UserDetail getUserDetailsByCustomerId(@PathVariable String customerId) {
        System.out.println("hiii");
        throw new RuntimeException("Simulated controller error");

//        return userService.getUserDetailByCustomerId(customerId);
    }
}

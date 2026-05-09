package com.application.ene.orgmanagement.auth.controller;

import com.application.ene.orgmanagement.auth.dto.RegistrationRequest;
import com.application.ene.orgmanagement.auth.dto.UserUpdateRequest;
import com.application.ene.orgmanagement.auth.entity.ClientPersonnelDetail;
import com.application.ene.orgmanagement.auth.service.AuthService;
import com.application.ene.orgmanagement.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user-service/employee")
@RequiredArgsConstructor
public class ClientPersonelController {

    private final UserService userService;
    private final AuthService authService;

    @GetMapping("/details/{userId}")
    public ClientPersonnelDetail getUserDetailsByUserId(@PathVariable String userId) {
        return userService.getClientPersonnelDetailByUserId(userId);
    }

    @PutMapping("/update-details")
    public void updateUserDetails(@PathVariable String userId, @RequestBody UserUpdateRequest request) {
        userService.updateUserDetails(userId, request);
    }

    @PostMapping("/upgrade")
    public String updateUserRole(@PathVariable String userId, @RequestBody RegistrationRequest request) {
        return authService.upgradeRole(userId, request);
    }
}

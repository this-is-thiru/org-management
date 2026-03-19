package com.application.ene.orgmanagement.auth.controller;

import com.application.ene.orgmanagement.auth.dto.RegistrationRequest;
import com.application.ene.orgmanagement.auth.dto.UserUpdateRequest;
import com.application.ene.orgmanagement.auth.entity.UserDetail;
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
@RequestMapping("/user-service")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthService authService;

    @GetMapping("/details/{userId}")
    public UserDetail getUserDetailsByUserId(@PathVariable String userId) {
        return userService.getUserDetailByUserId(userId);
    }

    @PutMapping("/update/details/{userId}")
    public void updateUserDetails(@PathVariable String userId, @RequestBody UserUpdateRequest request) {
        userService.updateUserDetails(userId, request);
    }

    @PostMapping("/upgrade/user/{userId}")
    public String updateUserRole(@PathVariable String userId, @RequestBody RegistrationRequest request) {
        return authService.upgradeRole(userId, request);
    }
}

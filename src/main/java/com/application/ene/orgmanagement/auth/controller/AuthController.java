package com.application.ene.orgmanagement.auth.controller;

import com.application.ene.orgmanagement.auth.dto.LoginRequest;
import com.application.ene.orgmanagement.auth.dto.LoginResponse;
import com.application.ene.orgmanagement.auth.dto.RegistrationRequest;
import com.application.ene.orgmanagement.auth.dto.RoleUpgradeRequest;
import com.application.ene.orgmanagement.auth.dto.UserIdDto;
import com.application.ene.orgmanagement.auth.entity.ClientPersonnelDetail;
import com.application.ene.orgmanagement.auth.service.AuthService;
import com.application.ene.orgmanagement.auth.service.AuthServiceHelper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/user-service")
public class AuthController {

    private final AuthService authService;
    private final AuthServiceHelper authServiceHelper;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return authServiceHelper.login(loginRequest);
    }

    @PutMapping("/user/{email}/change/password")
    public String changePassword(@PathVariable String email, @RequestBody RegistrationRequest createUserRequest) {
        return authService.changePassword(email, createUserRequest);
    }

    @PostMapping("/register")
    public UserIdDto addNewUser(@RequestBody RegistrationRequest request) {
        return authService.addUser(request);
    }

    @PostMapping("/employee/register")
    public UserIdDto addEmployee(@RequestBody RegistrationRequest request) {
        return authService.addEmployee(request);
    }

    @PostMapping("/employees/{clientId}")
    public List<ClientPersonnelDetail> getClientEmployees(@PathVariable String clientId) {
        return authService.getAllEmployees(clientId);
    }

    @PostMapping("/test")
    public RoleUpgradeRequest testUserRole(@RequestBody RoleUpgradeRequest request) {
        return request;
    }

    @GetMapping("/login")
    public String login1() {
        return "login";
    }
}

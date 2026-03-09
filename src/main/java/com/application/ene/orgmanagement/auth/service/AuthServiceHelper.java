package com.application.ene.orgmanagement.auth.service;


import com.application.ene.orgmanagement.auth.dto.LoginRequest;
import com.application.ene.orgmanagement.auth.dto.LoginResponse;
import com.application.ene.orgmanagement.auth.dto.LoginType;
import com.application.ene.orgmanagement.auth.entity.UserDetail;
import com.application.ene.orgmanagement.auth.repository.UserDetailsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class AuthServiceHelper {

    private final UserDetailsRepository userDetailsRepo;
    private final AuthenticationManager authenticationManager;


    public LoginResponse login(LoginRequest loginRequest) {
        if (loginRequest.getLoginType() == LoginType.CUSTOMER_ID_PASSWORD) {
            return authenticateAndGenerateToken(loginRequest.getUsername(), loginRequest.getPassword());
        }

        if (loginRequest.getLoginType() == LoginType.CLIENT_EMAIL_PASSWORD) {
            Optional<UserDetail> customerDetail = userDetailsRepo.findByClientIdAndEmail(loginRequest.getClientId(), loginRequest.getEmail());
            if (customerDetail.isPresent()) {
                UserDetail userDetail = customerDetail.get();
                return authenticateAndGenerateToken(userDetail.getCustomerId(), loginRequest.getPassword());
            }
        }

        throw new UsernameNotFoundException("Invalid login request");
    }

    public LoginResponse authenticateAndGenerateToken(String username, String password) {
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(auth);
        if (authentication.isAuthenticated()) {
            return AuthService.generateToken(username, authentication);
        } else {
            throw new UsernameNotFoundException("Invalid login request");
        }
    }
}
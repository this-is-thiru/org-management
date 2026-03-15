package com.application.ene.orgmanagement.complaint.integration.service;

import com.application.ene.orgmanagement.complaint.dto.proxy.response.UserDetailsResponse;
import com.application.ene.orgmanagement.complaint.integration.client.UserClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserGateway {

    private final UserClient userClient;

    @Retry(name = "user-service")
    @CircuitBreaker(name = "user-service", fallbackMethod = "fallback")
    public UserDetailsResponse getUserDetails(String customerId) {
        return userClient.getUserDetailsByCustomerId(customerId);
    }

    public UserDetailsResponse fallback(String customerId, Throwable ex) {
        UserDetailsResponse response = new UserDetailsResponse();
        response.setName("UNKNOWN");
        return response;
    }
}
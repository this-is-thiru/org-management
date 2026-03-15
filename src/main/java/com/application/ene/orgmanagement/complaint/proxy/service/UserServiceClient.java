package com.application.ene.orgmanagement.complaint.proxy.service;

import com.application.ene.orgmanagement.complaint.dto.proxy.response.UserDetailsResponse;
import com.application.ene.orgmanagement.complaint.proxy.client.UserClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserServiceClient {

    private final UserClient userClient;

    @Cacheable(value = "users", key = "#customerId")
    @Retry(name = "userService")
    @CircuitBreaker(name = "userService", fallbackMethod = "fallbackUser")
    public UserDetailsResponse getUserDetailsByCustomerId(String customerId) {
        return userClient.getUserDetailsByCustomerId(customerId);
    }

    public UserDetailsResponse fallbackUser(String customerId, Throwable ex) {
        log.error("Failed to fetch user details for customerId: {}", customerId, ex);
        return null;
    }
}
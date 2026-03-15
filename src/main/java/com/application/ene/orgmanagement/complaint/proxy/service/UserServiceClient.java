package com.application.ene.orgmanagement.complaint.proxy.service;

import com.application.ene.orgmanagement.complaint.dto.proxy.response.UserDetailsResponse;
import com.application.ene.orgmanagement.complaint.proxy.client.UserClient;
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
    public UserDetailsResponse getUserDetailsByCustomerId(String customerId) {
        return userClient.getUserDetailsByCustomerId(customerId);
    }
}
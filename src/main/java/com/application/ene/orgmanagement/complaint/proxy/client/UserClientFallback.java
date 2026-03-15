package com.application.ene.orgmanagement.complaint.proxy.client;

import com.application.ene.orgmanagement.complaint.dto.proxy.response.UserDetailsResponse;
import org.springframework.stereotype.Component;

@Component
public class UserClientFallback implements UserClient {

    @Override
    public UserDetailsResponse getUserDetailsByCustomerId(String customerId) {
        UserDetailsResponse response = new UserDetailsResponse();
        response.setId(null);
        response.setClientId(null);
        response.setCustomerId(customerId);
        response.setName("Unknown User");
        return response;
    }
}
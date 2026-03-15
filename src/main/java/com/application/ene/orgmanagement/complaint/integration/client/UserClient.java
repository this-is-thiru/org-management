package com.application.ene.orgmanagement.complaint.integration.client;

import com.application.ene.orgmanagement.complaint.dto.proxy.response.UserDetailsResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", url = "${services.user.url}")
public interface UserClient {
    @GetMapping("/details/{userId}")
    UserDetailsResponse getUserDetailsByUserId(@PathVariable String userId);
}

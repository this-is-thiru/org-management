package com.application.ene.orgmanagement.complaint.proxy.client;

import com.application.ene.orgmanagement.complaint.dto.proxy.response.UserDetailsResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", url = "http://user-service")
public interface UserClient {
    @GetMapping("/details/{customerId}")
    UserDetailsResponse getUserDetailsByCustomerId(@PathVariable String customerId);

}

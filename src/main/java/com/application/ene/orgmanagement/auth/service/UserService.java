package com.application.ene.orgmanagement.auth.service;


import com.application.ene.orgmanagement.auth.entity.UserDetail;
import com.application.ene.orgmanagement.auth.repository.UserDetailsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDetailsRepository userDetailsRepo;

    public UserDetail getUserDetailByCustomerId(String customerId) {
        Optional<UserDetail> customerDetailsOpt = userDetailsRepo.findByCustomerId(customerId);
        if (customerDetailsOpt.isPresent()) {
            return customerDetailsOpt.get();
        }
        throw new IllegalArgumentException("User with customerId " + customerId + " not found");
    }
}
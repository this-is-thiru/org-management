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

    public UserDetail getUserDetailByUserId(String userId) {
        Optional<UserDetail> userDetailOptional = userDetailsRepo.findByUserId(userId);
        if (userDetailOptional.isPresent()) {
            return userDetailOptional.get();
        }
        throw new IllegalArgumentException("User with userId " + userId + " not found");
    }
}
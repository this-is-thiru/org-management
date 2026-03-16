package com.application.ene.orgmanagement.auth.service;


import com.application.ene.orgmanagement.auth.dto.UserUpdateRequest;
import com.application.ene.orgmanagement.auth.entity.UserDetail;
import com.application.ene.orgmanagement.auth.repository.UserDetailsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
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

    public void updateUserDetails(String userId, UserUpdateRequest request) {
        Optional<UserDetail> userDetailOptional = userDetailsRepo.findByUserId(userId);
        if (userDetailOptional.isEmpty()) {
            throw new IllegalArgumentException("User with userId " + userId + " not found");
        }

        UserDetail userDetail = userDetailOptional.get();
        if (StringUtils.isNotBlank(request.email())) {
            userDetail.setEmail(request.email());
        }

        if (StringUtils.isNotBlank(request.name())) {
            userDetail.setName(request.name());
        }

        if (StringUtils.isNotBlank(request.mobileNumber())) {
            userDetail.setMobileNumber(request.mobileNumber());
        }

        userDetailsRepo.save(userDetail);
    }
}
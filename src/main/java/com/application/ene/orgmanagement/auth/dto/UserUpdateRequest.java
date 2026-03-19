package com.application.ene.orgmanagement.auth.dto;

public record UserUpdateRequest (
        String name,
        String email,
        String mobileNumber
) {
}

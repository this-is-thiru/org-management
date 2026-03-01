package com.application.ene.orgmanagement.auth.dto;

import lombok.Data;

import java.util.List;

@Data
public class RoleUpgradeRequest {
    private String email;
    private List<AuthHelper.Role> role;
}

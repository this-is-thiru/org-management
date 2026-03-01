package com.application.ene.orgmanagement.auth.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class UserDetail {
    @Id
    private Long id;
    private String clientId = "KANHA1";
    private String customerId;
    private String email;
    private String password;
    private boolean status;
    private String roles;
}
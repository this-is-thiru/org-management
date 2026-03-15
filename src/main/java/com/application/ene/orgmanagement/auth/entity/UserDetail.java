package com.application.ene.orgmanagement.auth.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Entity
@Table(
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_client_email", columnNames = {"client_id", "email"})
        }
)
@EntityListeners(AuditingEntityListener.class)
public class UserDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String clientId = "KANHA1";
    private String customerId;
    private String name;
    private String email;
    private String password;
    private boolean status;
    private String roles;
}
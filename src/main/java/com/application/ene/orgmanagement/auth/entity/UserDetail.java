package com.application.ene.orgmanagement.auth.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.Version;
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
    private String userId;
    private String name;
    private String email;
    private String mobileNumber;
    private String password;
    private boolean status = true;
    private String roles;

    @Version
    private int version;
}
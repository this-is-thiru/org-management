package com.application.ene.orgmanagement.auth.repository;

import com.application.ene.orgmanagement.auth.entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDetailsRepository extends JpaRepository<UserDetail, String> {

	Optional<UserDetail> findByCustomerId(String customerId);

	Optional<UserDetail> findByEmail(String email);

	Optional<UserDetail> findByClientIdAndEmail(String clientId, String email);
}
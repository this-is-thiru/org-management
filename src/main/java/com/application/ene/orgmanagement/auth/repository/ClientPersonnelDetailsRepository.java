package com.application.ene.orgmanagement.auth.repository;

import com.application.ene.orgmanagement.auth.entity.ClientPersonnelDetail;
import com.application.ene.orgmanagement.auth.entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClientPersonnelDetailsRepository extends JpaRepository<ClientPersonnelDetail, String> {

	Optional<ClientPersonnelDetail> findByUserId(String userId);

	Optional<ClientPersonnelDetail> findByEmail(String email);

	List<ClientPersonnelDetail> findByClientIdAndStatusIsTrue(String clientId);

	Optional<ClientPersonnelDetail> findByClientIdAndEmail(String clientId, String email);
}
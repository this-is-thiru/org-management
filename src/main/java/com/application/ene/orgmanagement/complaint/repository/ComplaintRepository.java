package com.application.ene.orgmanagement.complaint.repository;

import com.application.ene.orgmanagement.complaint.entity.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComplaintRepository extends JpaRepository<Complaint, String> {
    List<Complaint> findByClientIdAndStatusId(String clientId, Integer statusId);

    List<Complaint> findByClientIdAndCustomerId(String clientId, String customerId);

    List<Complaint> findByClientId(String clientId);

    List<Complaint> findByEscalateTo(String personnelId);
}

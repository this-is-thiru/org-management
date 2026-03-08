package com.application.ene.orgmanagement.complaint.repository;

import com.application.ene.orgmanagement.complaint.entity.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
    List<Complaint> findByClientIdAndStatusId(String clientId, Integer statusId);

    List<Complaint> findByEscalateTo(String personnelId);
}

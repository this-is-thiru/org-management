package com.application.ene.orgmanagement.complaint.repository;

import com.application.ene.orgmanagement.complaint.entity.ComplaintStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComplaintStatusRepository extends JpaRepository<ComplaintStatus, String> {
//    List<ComplaintStatus> findByClientId(String clientId, Integer statusId);
//    int countByClientId(String clientId);

    List<ComplaintStatus> findByClientId(String clientId);
}

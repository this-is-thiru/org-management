package com.application.ene.orgmanagement.complaint.service;

import com.application.ene.orgmanagement.complaint.dto.request.ComplaintStatusCreationRequest;
import com.application.ene.orgmanagement.complaint.entity.ComplaintStatus;
import com.application.ene.orgmanagement.complaint.exception.ComplaintServiceException;
import com.application.ene.orgmanagement.complaint.repository.ComplaintStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ComplainStatusService {
    private final ComplaintStatusRepository complaintStatusRepository;
    public void createComplaintStatus(ComplaintStatusCreationRequest request) {
        int currentStatusesCount = complaintStatusRepository.countByClientId(request.getClientId());
        if (currentStatusesCount >= 5) {
            throw new ComplaintServiceException("Maximum number of complaint statuses reached for client: " + request.getClientId());
        }
        ComplaintStatus complaintStatus = new ComplaintStatus();
        complaintStatus.setClientId(request.getClientId());
        complaintStatus.setTitle(request.getTitle());
        complaintStatus.setName(request.getName());
        complaintStatus.setDescription(request.getDescription());
        complaintStatusRepository.save(complaintStatus);
    }
}

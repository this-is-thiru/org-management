package com.application.ene.orgmanagement.complaint.service;

import com.application.ene.orgmanagement.complaint.dto.request.ComplaintStatusCreationRequest;
import com.application.ene.orgmanagement.complaint.entity.ComplaintStatus;
import com.application.ene.orgmanagement.complaint.exception.ComplaintServiceException;
import com.application.ene.orgmanagement.complaint.repository.ComplaintStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ComplainStatusService {
    private final ComplaintStatusRepository complaintStatusRepository;
    public void createComplaintStatus(ComplaintStatusCreationRequest request) {
        List<ComplaintStatus> currentStatuses = complaintStatusRepository.findByClientId(request.getClientId());
        boolean statusExists = currentStatuses.stream().anyMatch(status -> status.getName().equals(request.getName()));
        if (statusExists) {
            throw new ComplaintServiceException("Complaint status with name: " + request.getName() + " already exists for client: " + request.getClientId());
        }
        if (currentStatuses.size() > 5) {
            throw new ComplaintServiceException("Maximum number of complaint statuses reached for client: " + request.getClientId());
        }
        ComplaintStatus complaintStatus = new ComplaintStatus();
        complaintStatus.setCurrentStatusId(currentStatuses.size() + 1);
        complaintStatus.setClientId(request.getClientId());
        complaintStatus.setTitle(request.getTitle());
        complaintStatus.setName(request.getName());
        complaintStatus.setDescription(request.getDescription());
        complaintStatusRepository.save(complaintStatus);
    }

    public List<ComplaintStatus> getClientComplaintStatuses(String clientId) {
        return complaintStatusRepository.findByClientId(clientId);
    }
}

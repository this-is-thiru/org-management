package com.application.ene.orgmanagement.complaint.service;

import com.application.ene.orgmanagement.complaint.dto.ComplaintStatusUpdate;
import com.application.ene.orgmanagement.complaint.dto.request.ComplaintCreationDto;
import com.application.ene.orgmanagement.complaint.dto.request.ComplaintUpdateDto;
import com.application.ene.orgmanagement.complaint.entity.Complaint;
import com.application.ene.orgmanagement.complaint.repository.ComplaintRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ComplaintManager {
    private final ComplaintRepository complaintRepository;

    public long createComplaint(ComplaintCreationDto request) {
        Complaint complaint = new Complaint();
        complaint.setClientId(request.getClientId());
        complaint.setCustomerId(request.getCustomerId());
        complaint.setTitle(request.getTitle());
        complaint.setDescription(request.getDescription());
        complaint.setStatusId(request.getStatusId());
        complaint.setPriority(request.getPriority());
        complaint.setAssignedTo(request.getAssignedTo());
        complaint.setReportedBy(request.getReportedBy());

        ComplaintStatusUpdate statusUpdate = ComplaintStatusUpdate.builder().statusId(request.getStatusId()).notes(request.getNotes())
                .createdAt(LocalDateTime.now()).updatedAt(LocalDateTime.now()).updatedBy(request.getReportedBy()).build();
        complaint.getStatusUpdates().add(statusUpdate);

        var savedComplaint = complaintRepository.save(complaint);
        return savedComplaint.getId();
    }

    public void updateComplaintStatus(Long complaintId, ComplaintUpdateDto request) {

        Complaint complaint = complaintRepository.findById(complaintId).orElseThrow(() -> new RuntimeException("Complaint not found with id: " + complaintId));
        complaint.setStatusId(request.getStatusId());
        ComplaintStatusUpdate statusUpdate = ComplaintStatusUpdate.builder().statusId(request.getStatusId()).notes(request.getNotes())
                .createdAt(LocalDateTime.now()).updatedAt(LocalDateTime.now()).updatedBy(request.getUpdatedBy()).build();
        complaint.getStatusUpdates().add(statusUpdate);
        complaintRepository.save(complaint);
    }

}

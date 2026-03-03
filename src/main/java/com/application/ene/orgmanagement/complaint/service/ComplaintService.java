package com.application.ene.orgmanagement.complaint.service;

import com.application.ene.orgmanagement.common.config.SecurityAuditorAware;
import com.application.ene.orgmanagement.complaint.dto.ComplaintStatusUpdate;
import com.application.ene.orgmanagement.complaint.dto.request.ComplaintCreationDto;
import com.application.ene.orgmanagement.complaint.dto.request.ComplaintUpdateDto;
import com.application.ene.orgmanagement.complaint.entity.Complaint;
import com.application.ene.orgmanagement.complaint.repository.ComplaintRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ComplaintService {

    private static final Set<String> COMPLAINT_CATEGORIES = Set.of(
            "General", "Electrical", "Water", "Gas", "Heating", "Cooling", "Appliances", "Furniture",
            "Kitchen", "Bathroom", "Living Room", "Bedroom", "Office", "Garage", "Basement", "Attic",
            "Storage", "Barn", "Carpet", "Floor", "Ceiling", "Shelves", "Cabinets", "Bookcases"
    );

    private final ComplaintRepository complaintRepository;
    private final SecurityAuditorAware securityAuditorAware;

    public long createComplaint(ComplaintCreationDto request) {
        validateComplaint(request);
        Complaint complaint = new Complaint();
        complaint.setClientId(request.getClientId());
        complaint.setCustomerId(request.getCustomerId());
        complaint.setTitle(request.getTitle());
        complaint.setCategory(request.getCategory());
        complaint.setRoomNumber(request.getRoomNumber());
        complaint.setDescription(request.getDescription());
        complaint.setStatusId(request.getStatusId());
        complaint.setPriority(request.getPriority());
        complaint.setAssignedTo(request.getAssignedTo());
        complaint.setReportedBy(securityAuditorAware.getAuditor());

        ComplaintStatusUpdate statusUpdate = ComplaintStatusUpdate.builder().statusId(request.getStatusId()).notes(request.getNotes())
                .createdAt(Instant.now()).updatedAt(Instant.now()).updatedBy(securityAuditorAware.getAuditor()).build();
        complaint.getStatusUpdates().add(statusUpdate);

        var savedComplaint = complaintRepository.save(complaint);
        return savedComplaint.getId();
    }

    public List<Complaint> getComplaintsByStatus(String clientId, Integer statusId) {
        return complaintRepository.findByClientIdAndStatusId(clientId, statusId);
    }

    public void updateComplaintStatus(Long complaintId, ComplaintUpdateDto request) {

        validateComplaint(request);
        Complaint complaint = complaintRepository.findById(complaintId).orElseThrow(() -> new RuntimeException("Complaint not found with id: " + complaintId));
        complaint.setStatusId(request.getStatusId());
        complaint.setCategory(request.getCategory());
        ComplaintStatusUpdate statusUpdate = ComplaintStatusUpdate.builder().statusId(request.getStatusId()).notes(request.getNotes())
                .createdAt(Instant.now()).updatedAt(Instant.now()).updatedBy(securityAuditorAware.getAuditor()).build();
        complaint.getStatusUpdates().add(statusUpdate);
        complaintRepository.save(complaint);
    }

    private void validateComplaint(ComplaintCreationDto request) {
        if (request.getCategory() != null && !COMPLAINT_CATEGORIES.contains(request.getCategory())) {
            throw new IllegalArgumentException("Invalid category: " + request.getCategory());
        }
    }

    private void validateComplaint(ComplaintUpdateDto request) {
        if (request.getCategory() != null && !COMPLAINT_CATEGORIES.contains(request.getCategory())) {
            throw new IllegalArgumentException("Invalid category: " + request.getCategory());
        }
    }

}

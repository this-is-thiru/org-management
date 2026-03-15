package com.application.ene.orgmanagement.complaint.service;

import com.application.ene.orgmanagement.common.config.SecurityAuditorAware;
import com.application.ene.orgmanagement.common.util.TJsonMapper;
import com.application.ene.orgmanagement.complaint.dto.ComplaintStatusUpdate;
import com.application.ene.orgmanagement.complaint.dto.UserDto;
import com.application.ene.orgmanagement.complaint.dto.proxy.response.UserDetailsResponse;
import com.application.ene.orgmanagement.complaint.dto.request.ComplaintCreationDto;
import com.application.ene.orgmanagement.complaint.dto.request.ComplaintUpdateDto;
import com.application.ene.orgmanagement.complaint.dto.response.ComplaintResponse;
import com.application.ene.orgmanagement.complaint.entity.Complaint;
import com.application.ene.orgmanagement.complaint.exception.ComplaintServiceException;
import com.application.ene.orgmanagement.complaint.integration.service.UserGateway;
import com.application.ene.orgmanagement.complaint.repository.ComplaintRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ComplaintService {

    private static final String ESCALATED = "ESCALATED";

    private static final Set<String> COMPLAINT_CATEGORIES = Set.of(
            "General", "Electrical", "Water", "Gas", "Heating", "Cooling", "Appliances", "Furniture",
            "Kitchen", "Bathroom", "Living Room", "Bedroom", "Office", "Garage", "Basement", "Attic",
            "Storage", "Barn", "Carpet", "Floor", "Ceiling", "Shelves", "Cabinets", "Bookcases"
    );

    private final ComplaintRepository complaintRepository;
    private final SecurityAuditorAware securityAuditorAware;

    private final UserGateway userGateway;

    public String createComplaint(ComplaintCreationDto request) {
        validateComplaint(request);
        Complaint complaint = new Complaint();
        complaint.setClientId(request.getClientId());
        complaint.setUserId(request.getUserId());
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

    public List<ComplaintResponse> getComplaintsByStatus(String clientId, Integer statusId) {
        var complaintsWithStatuses = complaintRepository.findByClientIdAndStatusId(clientId, statusId);
        return getComplaintResponses(complaintsWithStatuses);
    }

    public List<ComplaintResponse> getUserComplaints(String clientId, String userId) {
        List<Complaint> userComplaints = complaintRepository.findByClientIdAndUserId(clientId, userId);
        return getComplaintResponses(userComplaints);
    }

    public List<ComplaintResponse> getClientComplaints(String clientId) {
        List<Complaint> clientComplaints = complaintRepository.findByClientId(clientId);
        return getComplaintResponses(clientComplaints);
    }

    public void updateComplaintStatus(String complaintId, ComplaintUpdateDto request) {

        validateComplaint(request);
        Complaint complaint = complaintRepository.findById(complaintId).orElseThrow(() -> new ComplaintServiceException("Complaint not found with id: " + complaintId));
        complaint.setStatusId(request.getStatusId());
        complaint.setCategory(request.getCategory());
        ComplaintStatusUpdate statusUpdate = ComplaintStatusUpdate.builder().statusId(request.getStatusId()).notes(request.getNotes())
                .createdAt(Instant.now()).updatedAt(Instant.now()).updatedBy(securityAuditorAware.getAuditor()).build();
        complaint.getStatusUpdates().add(statusUpdate);
        complaintRepository.save(complaint);
    }

    public void escalateTo(String complaintId, ComplaintUpdateDto request) {
        validateComplaint(request);
        Complaint complaint = complaintRepository.findById(complaintId).orElseThrow(() -> new ComplaintServiceException("Complaint not found with id: " + complaintId));
        complaint.setEscalateTo(request.getEscalateTo());
        ComplaintStatusUpdate statusUpdate = ComplaintStatusUpdate.builder().statusId(request.getStatusId()).status(ESCALATED).notes(request.getNotes())
                .createdAt(Instant.now()).updatedAt(Instant.now()).updatedBy(securityAuditorAware.getAuditor()).build();
        complaint.getStatusUpdates().add(statusUpdate);
        complaintRepository.save(complaint);
    }

    public List<Complaint> escalatedComplaints(String clientPersonnelId) {
        return complaintRepository.findByEscalateTo(clientPersonnelId);
    }

    public List<String> getComplaintCategories() {
        return COMPLAINT_CATEGORIES.stream().toList();
    }

    private List<ComplaintResponse> getComplaintResponses(List<Complaint> complaints) {
        return complaints.stream().map(this::getUserComplaintResponse).toList();
    }

    private ComplaintResponse getUserComplaintResponse(Complaint complaint) {
        ComplaintResponse complaintResponse = TJsonMapper.copy(complaint, ComplaintResponse.class);
        complaintResponse.setUserDetails(getUserDetails(complaint.getUserId()));
        complaintResponse.setAssignedToDetails(getUserDetails(complaint.getAssignedTo()));
        complaintResponse.setEscalateToDetails(getUserDetails(complaint.getEscalateTo()));
        complaintResponse.setReportedByDetails(getUserDetails(complaint.getReportedBy()));
        return complaintResponse;
    }

    public UserDto getUserDetails(String userId) {
        var userDetails = getUserDetail(userId);
        return new UserDto(userDetails.getUserId(), userDetails.getName());
    }

    private UserDetailsResponse getUserDetail(String userId) {
        if (userId == null) {
            return new UserDetailsResponse();
        }
        return userGateway.getUserDetails(userId);
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

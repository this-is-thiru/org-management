package com.application.ene.orgmanagement.complaint.controller;

import com.application.ene.orgmanagement.complaint.dto.request.ComplaintCreationDto;
import com.application.ene.orgmanagement.complaint.dto.request.ComplaintUpdateDto;
import com.application.ene.orgmanagement.complaint.dto.response.ComplaintResponse;
import com.application.ene.orgmanagement.complaint.entity.Complaint;
import com.application.ene.orgmanagement.complaint.service.ComplaintService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("complaint-service")
@RequiredArgsConstructor
public class ComplaintController {
    private final ComplaintService complaintService;

    @PostMapping("/complaint/create/client/{clientId}/user/{userId}")
    public String createComplaint(@PathVariable String clientId, @PathVariable String userId, @RequestBody ComplaintCreationDto request) {
        request.setClientId(clientId);
        request.setUserId(userId);
        return complaintService.createComplaint(request);
    }

    @PostMapping("/complaint/update/{complaintId}")
    public void updateComplaintStatus(@PathVariable String complaintId, @RequestBody ComplaintUpdateDto request) {
        complaintService.updateComplaintStatus(complaintId, request);
    }

    @PostMapping("/complaint/escalate-to/{complaintId}")
    public void escalateTo(@PathVariable String complaintId, @RequestBody ComplaintUpdateDto request) {
        complaintService.escalateTo(complaintId, request);
    }

    @GetMapping("/complaint/categories/all")
    public List<String> getComplaintCategories() {
        return complaintService.getComplaintCategories();
    }

    @GetMapping("/complaints/client/{clientId}/user/{userId}")
    public List<ComplaintResponse> getUserComplaints(@PathVariable String clientId, @PathVariable String userId) {
        return complaintService.getUserComplaints(clientId, userId);
    }

    @GetMapping("/complaints/client/{clientId}")
    public List<ComplaintResponse> getClientsComplaints(@PathVariable String clientId) {
        return complaintService.getClientComplaints(clientId);
    }

    @GetMapping("/complaints/client/{clientId}/status/{statusId}")
    public List<ComplaintResponse> getComplaintsByStatus(@PathVariable String clientId, @PathVariable Integer statusId) {
        return complaintService.getComplaintsByStatus(clientId, statusId);
    }

    @GetMapping("complaints/escalated-to/user/{userId}")
    public List<Complaint> escalatedComplaints(@PathVariable String userId) {
        return complaintService.escalatedComplaints(userId);
    }

}

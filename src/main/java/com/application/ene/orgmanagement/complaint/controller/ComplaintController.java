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

    @PostMapping("/complaint/create/client/{clientId}/customer/{customerId}")
    public String createComplaint(@PathVariable String clientId, @PathVariable String customerId, @RequestBody ComplaintCreationDto request) {
        request.setClientId(clientId);
        request.setCustomerId(customerId);
        return complaintService.createComplaint(request);
    }

    @GetMapping("/complaint/client/{clientId}/customer/{customerId}")
    public List<ComplaintResponse> getCustomerComplaints(@PathVariable String clientId, @PathVariable String customerId) {
        return complaintService.getCustomerComplaints(clientId, customerId);
    }

    @GetMapping("/complaint/client/{clientId}")
    public List<Complaint> getClientsComplaints(@PathVariable String clientId) {
        return complaintService.getClientComplaints(clientId);
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

    @GetMapping("/complaints/client/{clientId}/status/{statusId}")
    public List<Complaint> getComplaintsByStatus(@PathVariable String clientId, @PathVariable Integer statusId) {
        return complaintService.getComplaintsByStatus(clientId, statusId);
    }

    @GetMapping("complaints/escalated-to/customer/{customerId}")
    public List<Complaint> escalatedComplaints(@PathVariable String customerId) {
        return complaintService.escalatedComplaints(customerId);
    }

}

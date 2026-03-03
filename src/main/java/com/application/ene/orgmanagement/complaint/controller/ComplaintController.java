package com.application.ene.orgmanagement.complaint.controller;

import com.application.ene.orgmanagement.complaint.dto.request.ComplaintCreationDto;
import com.application.ene.orgmanagement.complaint.dto.request.ComplaintUpdateDto;
import com.application.ene.orgmanagement.complaint.entity.Complaint;
import com.application.ene.orgmanagement.complaint.service.ComplaintService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("complaints")
@RequiredArgsConstructor
public class ComplaintController {
    private final ComplaintService complaintService;

    @PostMapping("/create/client/{clientId}/customer/{customerId}")
    public long createComplaint(@PathVariable String clientId, @PathVariable String customerId, @RequestBody ComplaintCreationDto request) {
        request.setClientId(clientId);
        request.setCustomerId(customerId);
        return complaintService.createComplaint(request);
    }

    @GetMapping("/client/{clientId}/status/{statusId}")
    public List<Complaint> getComplaintsByStatus(@PathVariable String clientId, @PathVariable Integer statusId) {
        return complaintService.getComplaintsByStatus(clientId, statusId);
    }

    @PutMapping("/update/{complaintId}")
    public void createComplaint(@PathVariable Long complaintId, @RequestBody ComplaintUpdateDto request) {
        complaintService.updateComplaintStatus(complaintId, request);
    }
}

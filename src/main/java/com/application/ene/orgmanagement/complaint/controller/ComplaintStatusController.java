package com.application.ene.orgmanagement.complaint.controller;

import com.application.ene.orgmanagement.complaint.dto.request.ComplaintStatusCreationRequest;
import com.application.ene.orgmanagement.complaint.entity.ComplaintStatus;
import com.application.ene.orgmanagement.complaint.service.ComplainStatusService;
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
public class ComplaintStatusController {
    private final ComplainStatusService complaintStatusManager;

    @PostMapping("/complaint-status/create/client/{clientId}")
    public void createComplaintStatus(@PathVariable String clientId, @RequestBody ComplaintStatusCreationRequest request) {
        request.setClientId(clientId);
        complaintStatusManager.createComplaintStatus(request);
    }

    @GetMapping("/complaint-status/all/client/{clientId}")
    public List<ComplaintStatus> getClientComplaintStatuses(@PathVariable String clientId) {
        return complaintStatusManager.getClientComplaintStatuses(clientId);
    }

}

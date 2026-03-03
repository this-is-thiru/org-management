package com.application.ene.orgmanagement.complaint.controller;

import com.application.ene.orgmanagement.complaint.dto.request.ComplaintStatusCreationRequest;
import com.application.ene.orgmanagement.complaint.service.ComplainStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("complaint-status")
@RequiredArgsConstructor
public class ComplaintStatusController {
    private final ComplainStatusService complaintStatusManager;

    @PostMapping("/create/client/{clientId}")
    public void createComplaint(@PathVariable String clientId, @RequestBody ComplaintStatusCreationRequest request) {
        request.setClientId(clientId);
        complaintStatusManager.createComplaintStatus(request);
    }

}

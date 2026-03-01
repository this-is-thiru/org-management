package com.application.ene.orgmanagement.complaint.controller;

import com.application.ene.orgmanagement.complaint.dto.request.ComplaintStatusCreationRequest;
import com.application.ene.orgmanagement.complaint.service.ComplainStatusManager;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("complaint-status")
@RequiredArgsConstructor
public class ComplaintStatusController {
    private final ComplainStatusManager complaintStatusManager;

    @PostMapping("/create")
    public void createComplaint(@RequestBody ComplaintStatusCreationRequest request) {
        complaintStatusManager.createComplaintStatus(request);
    }

}

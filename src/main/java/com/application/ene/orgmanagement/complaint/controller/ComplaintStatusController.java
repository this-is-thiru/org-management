package com.application.ene.orgmanagement.complaint.controller;

import com.application.ene.orgmanagement.complaint.dto.request.ComplaintCreationDto;
import com.application.ene.orgmanagement.complaint.dto.request.ComplaintStatusCreationRequest;
import com.application.ene.orgmanagement.complaint.dto.request.ComplaintUpdateDto;
import com.application.ene.orgmanagement.complaint.service.ComplainStatusManager;
import com.application.ene.orgmanagement.complaint.service.ComplaintManager;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ComplaintStatusController {
    private final ComplainStatusManager complaintStatusManager;

    @PostMapping("/complaintstatus/create")
    public void createComplaint(@RequestBody ComplaintStatusCreationRequest request) {
        complaintStatusManager.createComplaintStatus(request);
    }

}

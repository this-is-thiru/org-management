package com.application.ene.orgmanagement.complaint.controller;

import com.application.ene.orgmanagement.complaint.service.HelperService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("complaint-service")
@RequiredArgsConstructor
public class HelperController {
    private final HelperService helperService;

    @PutMapping("/evict-cache")
    public void evictCache() {
        helperService.complaintServiceCacheEvict();
    }

}

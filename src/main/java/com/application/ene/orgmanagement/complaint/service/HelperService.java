package com.application.ene.orgmanagement.complaint.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Service
public class HelperService {
    @CacheEvict(cacheNames = {"users"})
    public void complaintServiceCacheEvict() {
    }
}

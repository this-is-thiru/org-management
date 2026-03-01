package com.application.ene.orgmanagement.complaint.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class ComplaintStatusUpdate {
    private int statusId;
    private String status;
    private List<String> notes;
    private Instant createdAt;
    private Instant updatedAt;
    private String updatedBy;
}

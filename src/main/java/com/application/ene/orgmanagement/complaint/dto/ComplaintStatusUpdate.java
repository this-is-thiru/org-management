package com.application.ene.orgmanagement.complaint.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ComplaintStatusUpdate {
    private int statusId;
    private String status;
    private List<String> notes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String updatedBy;
}

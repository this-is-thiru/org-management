package com.application.ene.orgmanagement.complaint.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class ComplaintCreationDto {
    private String clientId;
    private String userId;
    private String title;
    private String category;
    private String roomNumber;
    private String description;
    private List<String> notes;
    private Integer statusId;
    private String priority;
    private String assignedTo;
    private String reportedBy;
}

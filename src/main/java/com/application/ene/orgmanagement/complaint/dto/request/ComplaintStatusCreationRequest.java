package com.application.ene.orgmanagement.complaint.dto.request;

import lombok.Data;

@Data
public class ComplaintStatusCreationRequest {
    private String clientId;
    private String name;
    private String title;
    private String description;
}

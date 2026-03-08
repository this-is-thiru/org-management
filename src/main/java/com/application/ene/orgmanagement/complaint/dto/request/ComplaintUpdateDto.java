package com.application.ene.orgmanagement.complaint.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class ComplaintUpdateDto {
    private int statusId;
    private String category;
    private String updatedBy;
    private String escalateTo;
    private List<String> notes;
}

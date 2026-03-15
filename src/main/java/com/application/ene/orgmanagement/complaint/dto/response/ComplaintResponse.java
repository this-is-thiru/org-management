package com.application.ene.orgmanagement.complaint.dto.response;

import com.application.ene.orgmanagement.complaint.dto.ComplaintStatusUpdate;
import com.application.ene.orgmanagement.complaint.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComplaintResponse {
    private String id;
    private String clientId;
    private UserDto userDetails;
    private String roomNumber;
    private String title;
    private String category;
    private String description;
    private Integer statusId; // complaint_status entity
    private String priority;
    private UserDto assignedToDetails;
    private UserDto escalateToDetails;
    private UserDto reportedByDetails;
    private String resolution;
    private List<ComplaintStatusUpdate> statusUpdates = new ArrayList<>();
    private List<String> tags;
    private List<String> watchers; // user ids

    private String createdAt;
    private String updatedAt;
    private String createdBy;
    private String updatedBy;
}

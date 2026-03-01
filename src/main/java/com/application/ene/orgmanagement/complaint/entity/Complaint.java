package com.application.ene.orgmanagement.complaint.entity;

import com.application.ene.orgmanagement.complaint.dto.ComplaintStatusUpdate;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Complaint {
    @Id
    private Long id;
    private String clientId;
    private String customerId;
    private String title;
    private String description;
    private Integer statusId; // complaint_status entity
    private String priority;
    private String assignedTo;
    private String resolution;
    private String reportedBy;
    private List<ComplaintStatusUpdate> statusUpdates = new ArrayList<>();
    private List<String> tags;
    private List<String> watchers; // customer ids

    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;
    @CreatedBy
    private String createdBy;
    @LastModifiedBy
    private String updatedBy;
}

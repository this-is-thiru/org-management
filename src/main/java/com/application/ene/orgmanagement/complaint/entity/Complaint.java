package com.application.ene.orgmanagement.complaint.entity;

import com.application.ene.orgmanagement.common.util.JpaConverter;
import com.application.ene.orgmanagement.complaint.dto.ComplaintStatusUpdate;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String clientId;
    private String customerId;
    private String roomNumber;
    private String title;
    private String description;
    private Integer statusId; // complaint_status entity
    private String priority;
    private String assignedTo;
    private String resolution;
    private String reportedBy;
    @Lob
    @Column(columnDefinition = "BLOB")
    @Convert(converter = JpaConverter.ComplaintStatusToJsonBlobConverter.class)
    private List<ComplaintStatusUpdate> statusUpdates = new ArrayList<>();

    @Lob
    @Column(columnDefinition = "BLOB")
    @Convert(converter = JpaConverter.StringListToJsonBlobConverter.class)
    private List<String> tags;
    @Lob
    @Column(columnDefinition = "BLOB")
    @Convert(converter = JpaConverter.StringListToJsonBlobConverter.class)
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

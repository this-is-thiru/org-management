package com.application.ene.orgmanagement.complaint.entity;

import com.application.ene.orgmanagement.complaint.dto.ComplaintStatusUpdate;
import com.application.ene.orgmanagement.complaint.util.JpaConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class Complaint {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String clientId;
    private String userId;
    private String roomNumber;
    private String title;
    private String category;
    private String description;
    private Integer statusId; // complaint_status entity
    private String priority;
    private String assignedTo;
    private String escalateTo;
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
    private List<String> watchers; // user ids

    @CreatedDate
    private Instant createdAt;
    @LastModifiedDate
    private Instant updatedAt;
    @CreatedBy
    private String createdBy;
    @LastModifiedBy
    private String updatedBy;
}

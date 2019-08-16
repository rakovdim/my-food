package com.myfood.commons.model.entities;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
public abstract class AuditedEntity extends AbstractEntity {
    @CreatedBy
    private String createdBy;
    @LastModifiedBy
    private String modifiedBy;
    @CreatedDate
    private LocalDateTime createdWhen = LocalDateTime.now();
    @LastModifiedDate
    private LocalDateTime modifiedWhen = LocalDateTime.now();

    public AuditedEntity() {
    }

    public AuditedEntity(UUID id) {
        super(id);
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public LocalDateTime getCreatedWhen() {
        return createdWhen;
    }

    public LocalDateTime getModifiedWhen() {
        return modifiedWhen;
    }
}

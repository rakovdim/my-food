package com.myfood.commons.model.entities;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class AuditedEntity extends AbstractEntity {
    @Column
    @CreatedBy
    private String createdBy;
    @Column(nullable = false)
    @LastModifiedBy
    private String modifiedBy;
    @Column
    @CreatedDate
    private LocalDateTime createdWhen = LocalDateTime.now();
    @LastModifiedDate
    @Column
    private LocalDateTime modifiedWhen = LocalDateTime.now();

    public AuditedEntity() {
    }

    public AuditedEntity(Long id) {
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

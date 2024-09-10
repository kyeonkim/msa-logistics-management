package com.sparta.logistics.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class BaseEntity {

    @CreatedDate
    @Column(updatable = false, nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updatedAt;

    @CreatedBy
    @Column(updatable = false, nullable = true) //임시로 true처리
    @Temporal(TemporalType.TIMESTAMP)
    private String createdBy;

    @LastModifiedBy
    @Column(updatable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private String updatedBy;

    @Column(name = "deleted_at", nullable = true, updatable = true)
    private LocalDateTime deletedAt;

    @Column(name = "deleted_by", nullable = true, updatable = true)
    private String deletedBy;

    @Column(name = "is_deleted", nullable = false)
    public Boolean isDeleted = false;
}
package com.example.todoapp.common;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import java.time.LocalDateTime;

@Getter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class BaseEntity {

    @CreatedDate    // 생성 시간 자동 저장, update X
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate   // 업데이트 시간 자동 저장
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // soft delete
    @Column(name = "is_deleted", columnDefinition = "boolean default false")
    private Boolean isDeleted;

    @PrePersist // 비영속 -> 영속 되기전 실행
    private void prePersist() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        isDeleted = false;
    }
}
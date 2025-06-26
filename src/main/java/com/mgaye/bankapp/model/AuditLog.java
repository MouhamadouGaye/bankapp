package com.mgaye.bankapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Instant;
import java.util.UUID;

import org.hibernate.Hibernate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "audit_logs")
public class AuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String action;
    private String details;
    private UUID userId;
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @PrePersist
    public void onCreate() {
        this.createdAt = Instant.now();
    }

    // This is solid for most cases. But if you're using Hibernate or JPA lifecycle
    // callbacks,
    // it’s even better to do this instead:

    // @Column(name = "created_at", nullable = false, updatable = false)
    // private Instant createdAt ;
    // @PrePersist
    // public void prePersist() {
    // this.createdAt = Instant.now();
    // }
}
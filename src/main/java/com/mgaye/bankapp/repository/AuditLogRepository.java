package com.mgaye.bankapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mgaye.bankapp.model.AuditLog;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
}
package com.mgaye.bankapp.service;

import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.mgaye.bankapp.model.AuditLog;
import com.mgaye.bankapp.repository.AuditLogRepository;

@Service
@RequiredArgsConstructor
public class AuditService {
    private final AuditLogRepository auditLogRepository;

    public void log(String action, String details, UUID userId) {
        // AuditLog log = AuditLog.builder()
        // .action(action)
        // .details(details)
        // .userId(userId)
        // .build();
        // auditLogRepository.save(log);
        // |*******| You can user builder pattern or direct instantiation as below

        AuditLog log = new AuditLog();
        log.setAction(action);
        log.setDetails(details);
        log.setUserId(userId);
        log.setCreatedAt(Instant.now());
        auditLogRepository.save(log);

    }
}
package com.mgaye.bankapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.mgaye.bankapp.model.AuditLog;
import com.mgaye.bankapp.repository.AuditLogRepository;

@Service
@RequiredArgsConstructor
public class AuditService {
    private final AuditLogRepository auditLogRepository;

    public void log(String action, String details, Long userId) {
        AuditLog log = AuditLog.builder()
                .action(action)
                .details(details)
                .userId(userId)
                .build();
        auditLogRepository.save(log);
    }
}
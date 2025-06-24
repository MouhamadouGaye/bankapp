package com.mgaye.bankapp.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mgaye.bankapp.dto.request.TransactionRequest;
import com.mgaye.bankapp.dto.request.TransferRequest;
import com.mgaye.bankapp.model.User;
import com.mgaye.bankapp.service.TransactionService;

@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/deposit")
    public ResponseEntity<Void> deposit(
            @Valid @RequestBody TransactionRequest request,
            @AuthenticationPrincipal User user) {
        transactionService.deposit(request, user);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/withdraw")
    public ResponseEntity<Void> withdraw(
            @Valid @RequestBody TransactionRequest request,
            @AuthenticationPrincipal User user) {
        transactionService.withdraw(request, user);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/transfer")
    public ResponseEntity<Void> transfer(
            @Valid @RequestBody TransferRequest request,
            @AuthenticationPrincipal User user) {
        transactionService.transfer(request, user);
        return ResponseEntity.ok().build();
    }
}
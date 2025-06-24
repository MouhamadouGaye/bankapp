package com.mgaye.bankapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.mgaye.bankapp.dto.response.AccountResponse;
import com.mgaye.bankapp.model.User;
import com.mgaye.bankapp.service.AccountService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountResponse> createAccount(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(accountService.createAccount(user));
    }

    @GetMapping
    public ResponseEntity<List<AccountResponse>> getUserAccounts(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(accountService.getAccountsForUser(user.getId()));
    }

    @GetMapping("/{iban}")
    public ResponseEntity<AccountResponse> getAccountByIban(@PathVariable String iban) {
        // Note: In a real app, you'd add security here to ensure only the owner or an
        // admin can access this.
        // The service layer currently does this for transactions, but not for direct
        // lookups.
        return ResponseEntity.ok(accountService.getAccountByIban(iban));
    }
}
package com.mgaye.bankapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.mgaye.bankapp.dto.request.CreateAccountRequest;
import com.mgaye.bankapp.dto.response.AccountResponse;
import com.mgaye.bankapp.model.User;
import com.mgaye.bankapp.service.AccountService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping
    public ResponseEntity<List<AccountResponse>> getUserAccounts(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(accountService.getAccountsForUser(user.getId()));
    }

    // @PreAuthorize("hasRole('ADMIN')")
    // @PostMapping("/create")
    // public ResponseEntity<AccountResponse> createAccount(@AuthenticationPrincipal
    // User user) {
    // return ResponseEntity.ok(accountService.createAccount(user));
    // }
    @PostMapping("/admin/accounts")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AccountResponse> createAccountForUser(@RequestParam Long userId) {
        return ResponseEntity.ok(accountService.createAccountForUser(userId));
    }

    // @PostMapping("/admin/accounts")
    // @PreAuthorize("hasRole('ADMIN')")
    // public ResponseEntity<AccountResponse> createAccountForUser(@RequestBody
    // CreateAccountRequest request) {
    // return
    // ResponseEntity.ok(accountService.createAccountForUser(request.getUserId()));
    // }

    @GetMapping("/{iban}")
    public ResponseEntity<AccountResponse> getAccountByIban(@PathVariable String iban) {
        // Note: In a real app, you'd add security here to ensure only the owner or an
        // admin can access this.
        // The service layer currently does this for transactions, but not for direct
        // lookups.
        return ResponseEntity.ok(accountService.getAccountByIban(iban));
    }
}
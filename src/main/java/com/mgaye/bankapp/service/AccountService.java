package com.mgaye.bankapp.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.mgaye.bankapp.dto.response.AccountResponse;
import com.mgaye.bankapp.dto.response.UserSummary;
import com.mgaye.bankapp.model.Account;
import com.mgaye.bankapp.model.Role;
import com.mgaye.bankapp.model.User;
import com.mgaye.bankapp.repository.AccountRepository;
import com.mgaye.bankapp.repository.UserRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final AuditService auditService;

    // public AccountResponse createAccount(User user) {
    // Account newAccount = Account.builder()
    // .iban(generateIban())
    // .balance(BigDecimal.ZERO)
    // .user(user)
    // .build();
    // Account savedAccount = accountRepository.save(newAccount);
    // auditService.log("ACCOUNT_CREATED", "New account created with IBAN: " +
    // savedAccount.getIban(), user.getId());
    // return mapToAccountResponse(savedAccount);
    // }

    // public AccountResponse createAccountForUser(Long userId) {
    // User user = userRepository.findById(userId)
    // .orElseThrow(() -> new RuntimeException("User not found"));

    // Account newAccount = Account.builder()
    // .iban(generateIban())
    // .balance(BigDecimal.ZERO)
    // .user(user)
    // .build();

    // Account savedAccount = accountRepository.save(newAccount);
    // auditService.log("ACCOUNT_CREATED", "New account created with IBAN: " +
    // savedAccount.getIban(), user.getId());
    // return mapToAccountResponse(savedAccount);
    // }

    // public AccountResponse createAccountForUser(User user) {
    // // Optionally check if user already has an account
    // if (!user.getAccounts().isEmpty()) {
    // throw new IllegalStateException("User already has an account");
    // }

    // Account newAccount = Account.builder()
    // .iban(generateIban()) // Your IBAN generation logic
    // .balance(BigDecimal.ZERO)
    // .user(user)
    // .build();

    // Account savedAccount = accountRepository.save(newAccount);

    // auditService.log(
    // "ACCOUNT_CREATED_BY_ADMIN",
    // "Admin created account with IBAN: " + savedAccount.getIban() + " for user: "
    // + user.getEmail(),
    // user.getId());

    // return mapToAccountResponse(savedAccount);
    // }
    public AccountResponse createAccountForUser(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "User not found"));

        // Optional: check role if needed
        if (!user.getRole().equals(Role.USER)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot create account for non-user");
        }

        Account account = Account.builder()
                .iban(generateIban())
                .balance(BigDecimal.ZERO)
                .user(user)
                .build();

        Account savedAccount = accountRepository.save(account);

        auditService.log("ACCOUNT_CREATED",
                "Account created for user " + user.getEmail(),
                user.getId());

        return mapToAccountResponse(savedAccount);
    }

    // private String generateIban() {
    // // Dummy example IBAN generator
    // return "DE" + UUID.randomUUID().toString().replaceAll("-", "").substring(0,
    // 20).toUpperCase();
    // }

    public List<AccountResponse> getAccountsForUser(UUID userId) {
        return accountRepository.findByUserId(userId).stream()
                .map(this::mapToAccountResponse)
                .collect(Collectors.toList());
    }

    public AccountResponse getAccountByIban(String iban) {
        Account account = accountRepository.findByIban(iban)
                .orElseThrow(() -> new EntityNotFoundException("Account not found with IBAN: " + iban));
        return mapToAccountResponse(account);
    }

    private String generateIban() {
        // Simple IBAN generation for example purposes. Real IBANs have a structure.
        return "DE" + UUID.randomUUID().toString().replace("-", "").substring(0, 20).toUpperCase();
    }

    private AccountResponse mapToAccountResponse(Account account) {
        return AccountResponse.builder()
                .id(account.getId())
                .iban(account.getIban())
                .balance(account.getBalance())
                .user(UserSummary.builder().id(account.getUser().getId())
                        .firstName(account.getUser().getFirstName())
                        .lastName(account.getUser().getLastName())
                        .email(account.getUser().getEmail())
                        .role(account.getUser().getRole())
                        .build())
                .build();
    }
}
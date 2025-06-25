package com.mgaye.bankapp.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.mgaye.bankapp.dto.response.AccountResponse;
import com.mgaye.bankapp.model.Account;
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

    public AccountResponse createAccountForUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Account newAccount = Account.builder()
                .iban(generateIban())
                .balance(BigDecimal.ZERO)
                .user(user)
                .build();

        Account savedAccount = accountRepository.save(newAccount);
        auditService.log("ACCOUNT_CREATED", "New account created with IBAN: " +
                savedAccount.getIban(), user.getId());
        return mapToAccountResponse(savedAccount);
    }

    public List<AccountResponse> getAccountsForUser(Long userId) {
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
                .build();
    }
}
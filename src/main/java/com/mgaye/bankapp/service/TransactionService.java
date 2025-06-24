package com.mgaye.bankapp.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mgaye.bankapp.dto.request.TransactionRequest;
import com.mgaye.bankapp.dto.request.TransferRequest;
import com.mgaye.bankapp.exception.InsufficientFundsException;
import com.mgaye.bankapp.exception.UnauthorizedOperationException;
import com.mgaye.bankapp.model.Account;
import com.mgaye.bankapp.model.Transaction;
import com.mgaye.bankapp.model.TransactionType;
import com.mgaye.bankapp.model.User;
import com.mgaye.bankapp.repository.AccountRepository;
import com.mgaye.bankapp.repository.TransactionRepository;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final AuditService auditService;

    @Transactional
    public void deposit(TransactionRequest request, User user) {
        Account account = getAccountAndCheckOwnership(request.getIban(), user);
        account.setBalance(account.getBalance().add(request.getAmount()));
        accountRepository.save(account);

        Transaction transaction = Transaction.builder()
                .type(TransactionType.DEPOSIT)
                .amount(request.getAmount())
                .toAccount(account)
                .build();
        transactionRepository.save(transaction);
        auditService.log("DEPOSIT", "Amount " + request.getAmount() + " deposited to " + account.getIban(),
                user.getId());
    }

    @Transactional
    public void withdraw(TransactionRequest request, User user) {
        Account account = getAccountAndCheckOwnership(request.getIban(), user);

        if (account.getBalance().compareTo(request.getAmount()) < 0) {
            throw new InsufficientFundsException("Insufficient funds for withdrawal.");
        }

        account.setBalance(account.getBalance().subtract(request.getAmount()));
        accountRepository.save(account);

        Transaction transaction = Transaction.builder()
                .type(TransactionType.WITHDRAWAL)
                .amount(request.getAmount())
                .fromAccount(account)
                .build();
        transactionRepository.save(transaction);
        auditService.log("WITHDRAWAL", "Amount " + request.getAmount() + " withdrawn from " + account.getIban(),
                user.getId());
    }

    @Transactional
    public void transfer(TransferRequest request, User user) {
        if (request.getFromIban().equals(request.getToIban())) {
            throw new IllegalArgumentException("Source and destination accounts cannot be the same.");
        }

        Account fromAccount = getAccountAndCheckOwnership(request.getFromIban(), user);
        Account toAccount = accountRepository.findByIban(request.getToIban())
                .orElseThrow(() -> new EntityNotFoundException("Destination account not found."));

        if (fromAccount.getBalance().compareTo(request.getAmount()) < 0) {
            throw new InsufficientFundsException("Insufficient funds for transfer.");
        }

        fromAccount.setBalance(fromAccount.getBalance().subtract(request.getAmount()));
        toAccount.setBalance(toAccount.getBalance().add(request.getAmount()));

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        Transaction transaction = Transaction.builder()
                .type(TransactionType.TRANSFER)
                .amount(request.getAmount())
                .fromAccount(fromAccount)
                .toAccount(toAccount)
                .build();
        transactionRepository.save(transaction);
        auditService.log("TRANSFER",
                "Transferred " + request.getAmount() + " from " + fromAccount.getIban() + " to " + toAccount.getIban(),
                user.getId());
    }

    private Account getAccountAndCheckOwnership(String iban, User user) {
        Account account = accountRepository.findByIban(iban)
                .orElseThrow(() -> new EntityNotFoundException("Account not found with IBAN: " + iban));

        if (!account.getUser().getId().equals(user.getId())) {
            auditService.log("UNAUTHORIZED_ACCESS_ATTEMPT",
                    "User " + user.getEmail() + " tried to access account " + iban, user.getId());
            throw new UnauthorizedOperationException(
                    "You do not have permission to perform this operation on this account.");
        }
        return account;
    }
}
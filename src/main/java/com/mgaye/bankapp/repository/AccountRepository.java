package com.mgaye.bankapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mgaye.bankapp.model.Account;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {
    Optional<Account> findByIban(String iban);

    List<Account> findByUserId(UUID userId);
}
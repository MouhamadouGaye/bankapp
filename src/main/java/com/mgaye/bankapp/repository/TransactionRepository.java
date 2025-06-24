package com.mgaye.bankapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mgaye.bankapp.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
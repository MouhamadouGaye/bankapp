package com.mgaye.bankapp.dto.response;

import java.math.BigDecimal;
import java.util.UUID;

import com.mgaye.bankapp.model.AccountType;
import com.mgaye.bankapp.model.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Account
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponse {
    private UUID id;
    private String iban;
    private BigDecimal balance;
    private AccountType type;
    private String currency;
    private UserSummary user;
}

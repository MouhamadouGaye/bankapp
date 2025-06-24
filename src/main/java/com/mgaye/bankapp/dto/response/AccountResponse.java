package com.mgaye.bankapp.dto.response;

import java.math.BigDecimal;

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
    private Long id;
    private String iban;
    private BigDecimal balance;
}
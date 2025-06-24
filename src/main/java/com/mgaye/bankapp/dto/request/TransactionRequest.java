package com.mgaye.bankapp.dto.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Transaction
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequest {
    @NotBlank(message = "IBAN is required")
    private String iban;
    @NotNull
    @Positive(message = "Amount must be positive")
    private BigDecimal amount;
}
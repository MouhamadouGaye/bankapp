package com.mgaye.bankapp.dto.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransferRequest {
    @NotBlank(message = "Source IBAN is required")
    private String fromIban;
    @NotBlank(message = "Destination IBAN is required")
    private String toIban;
    @NotNull
    @Positive(message = "Amount must be positive")
    private BigDecimal amount;
}
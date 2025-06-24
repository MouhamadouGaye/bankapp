package com.mgaye.bankapp.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

/**
 * Exception thrown when a transaction cannot be processed due to insufficient
 * funds.
 * This exception is typically used in banking applications to indicate that a
 * withdrawal
 * or transfer cannot be completed because the account does not have enough
 * balance.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException(String message) {
        super(message);
    }
}

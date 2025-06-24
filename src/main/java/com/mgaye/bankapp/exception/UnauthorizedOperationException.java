package com.mgaye.bankapp.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class UnauthorizedOperationException extends RuntimeException {
    public UnauthorizedOperationException(String message) {
        super(message);
    }
}
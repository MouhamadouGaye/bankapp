package com.mgaye.bankapp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

// Error
@Data
@AllArgsConstructor
public class ErrorResponse {
    private int statusCode;
    private String message;
    private long timestamp;
}
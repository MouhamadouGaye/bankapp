package com.mgaye.bankapp.dto.request;

import java.util.UUID;

import lombok.Data;

@Data
public class CreateAccountRequest {
    private UUID userId;
}

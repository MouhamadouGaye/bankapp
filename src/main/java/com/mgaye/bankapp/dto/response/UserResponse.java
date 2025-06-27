package com.mgaye.bankapp.dto.response;

import java.util.UUID;

import com.mgaye.bankapp.model.Role;

import lombok.Builder;
import lombok.Data;

@Data
public class UserResponse {
    private UUID id;
    private String email;
    private String firstName;
    private String lastName;
    private Role role;
}

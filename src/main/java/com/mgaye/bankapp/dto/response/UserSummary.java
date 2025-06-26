package com.mgaye.bankapp.dto.response;

import java.util.UUID;

import com.mgaye.bankapp.model.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSummary {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
}
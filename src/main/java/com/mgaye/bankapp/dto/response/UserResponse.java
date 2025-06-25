package com.mgaye.bankapp.dto.response;

import com.mgaye.bankapp.model.Role;

import lombok.Data;

@Data
public class UserResponse {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private Role role;
}

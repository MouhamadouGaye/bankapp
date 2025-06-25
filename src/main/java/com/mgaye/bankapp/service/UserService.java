package com.mgaye.bankapp.service;

import java.util.List;

import com.mgaye.bankapp.dto.response.UserResponse;

public interface UserService {
    List<UserResponse> getAllUsers();

    List<UserResponse> getAdminUsers();

}

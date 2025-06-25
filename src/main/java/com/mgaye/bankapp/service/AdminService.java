package com.mgaye.bankapp.service;

import java.util.List;

import com.mgaye.bankapp.dto.response.UserResponse;

public interface AdminService {
    List<UserResponse> getUsersWithoutAccounts();
}

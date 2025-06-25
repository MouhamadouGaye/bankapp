package com.mgaye.bankapp.service.impl;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.mgaye.bankapp.dto.mapper.ModelMapper;
import com.mgaye.bankapp.dto.response.UserResponse;
import com.mgaye.bankapp.model.User;
import com.mgaye.bankapp.repository.UserRepository;
import com.mgaye.bankapp.service.AdminService;

@Repository
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;

    public AdminServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponse> getUsersWithoutAccounts() {
        List<User> users = userRepository.findUsersWithoutAccounts();
        List<UserResponse> response = users.stream()
                .map(ModelMapper::map)
                .toList();

        return response;
    }

}

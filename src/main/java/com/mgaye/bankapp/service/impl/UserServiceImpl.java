package com.mgaye.bankapp.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.sql.ast.tree.expression.Over;
import org.springframework.stereotype.Service;

import com.mgaye.bankapp.dto.mapper.ModelMapper;
import com.mgaye.bankapp.dto.response.UserResponse;
import com.mgaye.bankapp.model.Role;
import com.mgaye.bankapp.model.User;
import com.mgaye.bankapp.repository.UserRepository;
import com.mgaye.bankapp.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper; // Or manual mapping

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> modelMapper.map(user))
                .collect(Collectors.toList());
    }

    public List<UserResponse> getAdminUsers() {
        return userRepository.findAll().stream()
                .filter(user -> user.getRole() == Role.ADMIN)
                .map(ModelMapper::map)
                .collect(Collectors.toList());
    }
}

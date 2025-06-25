package com.mgaye.bankapp.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mgaye.bankapp.dto.mapper.ModelMapper;
import com.mgaye.bankapp.dto.response.UserResponse;
import com.mgaye.bankapp.model.User;
import com.mgaye.bankapp.repository.UserRepository;
import com.mgaye.bankapp.service.AdminService;
import com.mgaye.bankapp.service.UserService;

@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/api/v1")
public class AdminController {

    private final UserService userService;
    private final AdminService adminService;

    public AdminController(UserService userService, AdminService adminService) {
        this.userService = userService;
        this.adminService = adminService;

    }

    @GetMapping("/admin/users")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/users-without-accounts")
    public ResponseEntity<List<UserResponse>> getUsersWithoutAccounts() {
        return ResponseEntity.ok(adminService.getUsersWithoutAccounts());
    }

}

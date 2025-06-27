package com.mgaye.bankapp.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mgaye.bankapp.dto.request.AuthenticationRequest;
import com.mgaye.bankapp.dto.request.RegisterRequest;
import com.mgaye.bankapp.dto.response.AuthenticationResponse;
import com.mgaye.bankapp.dto.response.UserResponse;
import com.mgaye.bankapp.service.AuthenticationService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    // @PreAuthorize("hasRole('ADMIN')")
    // @GetMapping("/users-in-line")
    // public ResponseEntity<AuthenticationResponse> getAllUsers() {
    // return ResponseEntity.ok(service.getAllUsers());
    // }

    // @PreAuthorize("hasRole('ADMIN')")
    // @GetMapping("/users")
    // public ResponseEntity<List<UserResponse>> getAllUsers() {
    // return ResponseEntity.ok(service.getAllUsers());
    // }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users")
    public ResponseEntity<?> getALLUsers() {
        try {
            return ResponseEntity.ok(service.getALLUsers());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Something went wrong: " + e.getMessage());
        }
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthenticationResponse> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }
}
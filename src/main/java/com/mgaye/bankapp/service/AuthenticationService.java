package com.mgaye.bankapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mgaye.bankapp.dto.request.AuthenticationRequest;
import com.mgaye.bankapp.dto.request.RegisterRequest;
import com.mgaye.bankapp.dto.response.AuthenticationResponse;
import com.mgaye.bankapp.model.Role;
import com.mgaye.bankapp.model.User;
import com.mgaye.bankapp.repository.UserRepository;
import com.mgaye.bankapp.security.JwtService;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final AuditService auditService;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER) // Default role
                .build();
        var savedUser = userRepository.save(user);
        auditService.log("USER_REGISTER", "New user registered: " + savedUser.getEmail(), savedUser.getId());
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        auditService.log("USER_LOGIN", "User logged in: " + user.getEmail(), user.getId());
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
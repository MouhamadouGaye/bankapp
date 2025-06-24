package com.mgaye.bankapp.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NotBlank(message = "First name is required")
    private String firstName;
    @NotBlank(message = "Last name is required")
    private String lastName;
    @Email(message = "Email should be valid")
    @NotBlank
    private String email;
    @NotBlank
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;
}

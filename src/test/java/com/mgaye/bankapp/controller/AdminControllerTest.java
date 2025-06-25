package com.mgaye.bankapp.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.UUID;

import com.mgaye.bankapp.model.Role;
import com.mgaye.bankapp.model.User;
import com.mgaye.bankapp.repository.UserRepository;
import com.mgaye.bankapp.security.JwtService;

import jakarta.transaction.Transactional;

// @SpringBootTest
// @AutoConfigureMockMvc
// class AdminControllerTest {

//     @Autowired
//     private MockMvc mockMvc;

//     @Autowired
//     private JwtService jwtService;

//     @Autowired
//     private UserRepository userRepository;

//     @Test
//     void getAllUsers_asAdmin_shouldReturnOk() throws Exception {
//         // 1. Create a fake admin user
//         User admin = new User();
//         admin.setFirstName("admin");
//         admin.setLastName("admin");
//         admin.setEmail("admin@example.com");
//         admin.setPassword("password"); // doesn't matter for test
//         admin.setRole(Role.ADMIN);
//         userRepository.save(admin);

//         // 2. Generate a JWT for that user
//         String token = jwtService.generateToken(admin);

//         // 3. Perform the GET request with Authorization header
//         mockMvc.perform(get("/api/v1/admin/users")
//                 .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
//                 .andExpect(status().isOk()); // or .andExpect(jsonPath("$").isArray()) etc.
//     }

//     @Test
//     void getAllUsers_asUser_shouldReturnForbidden() throws Exception {
//         User user = new User();

//         user.setFirstName("user");
//         user.setLastName("user");
//         user.setEmail("user@example.com");
//         user.setPassword("password");
//         user.setRole(Role.USER);
//         userRepository.save(user);

//         String token = jwtService.generateToken(user);

//         mockMvc.perform(get("/api/v1/admin/users")
//                 .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
//                 .andExpect(status().isForbidden());
//     }
// }

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_METHOD) // default, shown for clarity
class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void cleanDatabase() {
        userRepository.deleteAll(); // cleans up any duplicate users
    }

    @Test
    void getAllUsers_asAdmin_shouldReturnOk() throws Exception {
        User admin = new User();
        admin.setFirstName("admin");
        admin.setLastName("admin");
        admin.setEmail("admin_" + UUID.randomUUID() + "@example.com");
        admin.setPassword(passwordEncoder.encode("password"));
        admin.setRole(Role.ADMIN);
        userRepository.save(admin);

        String token = jwtService.generateToken(admin);

        mockMvc.perform(get("/api/v1/admin/users")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andDo(print()) // <--- useful to inspect request/response
                .andExpect(status().isOk());
    }

    @Test
    void getAllUsers_asUser_shouldReturnForbidden() throws Exception {
        User user = new User();
        user.setFirstName("user");
        user.setLastName("user");
        user.setEmail("user_" + UUID.randomUUID() + "@example.com");
        user.setPassword(passwordEncoder.encode("password"));
        user.setRole(Role.USER);
        userRepository.save(user);

        String token = jwtService.generateToken(user);

        mockMvc.perform(get("/api/v1/admin/users")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andDo(print()) // <--- useful to inspect request/response
                .andExpect(status().isForbidden());
    }

}

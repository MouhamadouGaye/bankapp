package com.mgaye.bankapp.dto.mapper;

import org.springframework.stereotype.Component;

import com.mgaye.bankapp.dto.response.UserResponse;
import com.mgaye.bankapp.model.User;

@Component
public class ModelMapper {

    public UserResponse map(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setEmail(user.getEmail());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setRole(user.getRole());
        return response;
    }

}

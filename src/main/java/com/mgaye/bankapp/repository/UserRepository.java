package com.mgaye.bankapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mgaye.bankapp.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.id NOT IN (SELECT a.user.id FROM Account a)")
    List<User> findUsersWithoutAccounts();

}
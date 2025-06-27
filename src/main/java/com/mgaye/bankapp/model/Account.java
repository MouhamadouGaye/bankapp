package com.mgaye.bankapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(unique = true)
    private String iban;
    private BigDecimal balance;
    private String currency;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private AccountType type;

    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "user_id", nullable = false)
    // private User user;

    @ManyToOne(fetch = FetchType.EAGER) // change from LAZY to EAGER
    @JoinColumn(name = "user_id")
    private User user;
}
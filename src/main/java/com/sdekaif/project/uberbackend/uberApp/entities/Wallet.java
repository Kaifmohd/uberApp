package com.sdekaif.project.uberbackend.uberApp.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    private Double balance;

    @OneToMany(mappedBy = "Wallet", fetch = FetchType.LAZY)
    private List<WalletTransaction> transactions;
}

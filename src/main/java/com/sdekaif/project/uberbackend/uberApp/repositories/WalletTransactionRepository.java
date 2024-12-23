package com.sdekaif.project.uberbackend.uberApp.repositories;

import com.sdekaif.project.uberbackend.uberApp.entities.WalletTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletTransactionRepository extends JpaRepository<WalletTransaction, Long> {
}

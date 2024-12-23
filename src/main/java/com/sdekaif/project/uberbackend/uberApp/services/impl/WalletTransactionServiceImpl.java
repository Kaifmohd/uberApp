package com.sdekaif.project.uberbackend.uberApp.services.impl;

import com.sdekaif.project.uberbackend.uberApp.entities.WalletTransaction;
import com.sdekaif.project.uberbackend.uberApp.repositories.WalletTransactionRepository;
import com.sdekaif.project.uberbackend.uberApp.services.WalletTransactionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletTransactionServiceImpl implements WalletTransactionService {

    private final WalletTransactionRepository walletTransactionRepository;
    private final ModelMapper modelMapper;

    @Override
    public void createNewWalletTransaction(WalletTransaction walletTransaction) {
        walletTransactionRepository.save(walletTransaction);
    }
}

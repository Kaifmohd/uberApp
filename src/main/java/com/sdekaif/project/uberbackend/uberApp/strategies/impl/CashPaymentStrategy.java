package com.sdekaif.project.uberbackend.uberApp.strategies.impl;

import com.sdekaif.project.uberbackend.uberApp.entities.Driver;
import com.sdekaif.project.uberbackend.uberApp.entities.Payment;
import com.sdekaif.project.uberbackend.uberApp.entities.enums.PaymentStatus;
import com.sdekaif.project.uberbackend.uberApp.entities.enums.TransactionMethod;
import com.sdekaif.project.uberbackend.uberApp.repositories.PaymentRepository;
import com.sdekaif.project.uberbackend.uberApp.services.PaymentService;
import com.sdekaif.project.uberbackend.uberApp.services.WalletService;
import com.sdekaif.project.uberbackend.uberApp.strategies.PaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CashPaymentStrategy implements PaymentStrategy {

    private final WalletService walletService;
    private final PaymentRepository paymentRepository;


    @Override
    public void processPayment(Payment payment) {
        Driver driver = payment.getRide().getDriver();
//        Wallet driverWallet = walletService.findByUser(driver.getUser());
        double platformCommision = payment.getAmount() * PLATFORM_COMMISSION;
        walletService.deductMoneyFromWallet(driver.getUser(),platformCommision,null,payment.getRide(), TransactionMethod.RIDE);
        payment.setPaymentStatus(PaymentStatus.SUCCESSFUL);
        paymentRepository.save(payment);
    }
}

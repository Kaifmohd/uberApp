package com.sdekaif.project.uberbackend.uberApp.strategies.impl;

import com.sdekaif.project.uberbackend.uberApp.entities.Driver;
import com.sdekaif.project.uberbackend.uberApp.entities.Payment;
import com.sdekaif.project.uberbackend.uberApp.entities.Rider;
import com.sdekaif.project.uberbackend.uberApp.entities.enums.PaymentStatus;
import com.sdekaif.project.uberbackend.uberApp.entities.enums.TransactionMethod;
import com.sdekaif.project.uberbackend.uberApp.repositories.PaymentRepository;
import com.sdekaif.project.uberbackend.uberApp.services.PaymentService;
import com.sdekaif.project.uberbackend.uberApp.services.WalletService;
import com.sdekaif.project.uberbackend.uberApp.strategies.PaymentStrategy;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletPaymentStrategy implements PaymentStrategy {

    private final WalletService walletService;
    private final PaymentRepository paymentRepository;

    @Override
    @Transactional
    public void processPayment(Payment payment) {
        Driver driver = payment.getRide().getDriver();
        Rider rider = payment.getRide().getRider();

        walletService.deductMoneyFromWallet(rider.getUser(),payment.getAmount(),null, payment.getRide(), TransactionMethod.RIDE);

        double driversCut = payment.getAmount() * (1 - PLATFORM_COMMISSION);

        walletService.addMoneyToWallet(driver.getUser(),driversCut,null,payment.getRide(),TransactionMethod.RIDE);

        payment.setPaymentStatus(PaymentStatus.SUCCESSFUL);
        paymentRepository.save(payment);
    }
}

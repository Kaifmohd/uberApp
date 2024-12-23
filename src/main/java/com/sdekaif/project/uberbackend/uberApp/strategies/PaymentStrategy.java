package com.sdekaif.project.uberbackend.uberApp.strategies;

import com.sdekaif.project.uberbackend.uberApp.entities.Payment;

public interface PaymentStrategy {
    Double PLATFORM_COMMISSION=0.3;

    void processPayment(Payment payment);
}
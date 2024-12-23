package com.sdekaif.project.uberbackend.uberApp.services;

import com.sdekaif.project.uberbackend.uberApp.entities.Payment;
import com.sdekaif.project.uberbackend.uberApp.entities.Ride;
import com.sdekaif.project.uberbackend.uberApp.entities.enums.PaymentStatus;

public interface PaymentService {

    void processPayment(Ride ride);

    Payment createNewPayment(Ride ride);

    void updatePaymentStatus(Payment payment, PaymentStatus paymentStatus);
}

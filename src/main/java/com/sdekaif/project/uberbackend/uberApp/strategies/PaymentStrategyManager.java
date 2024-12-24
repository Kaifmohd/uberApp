package com.sdekaif.project.uberbackend.uberApp.strategies;


import com.sdekaif.project.uberbackend.uberApp.entities.enums.PaymentMethod;
import com.sdekaif.project.uberbackend.uberApp.strategies.impl.CashPaymentStrategy;
import com.sdekaif.project.uberbackend.uberApp.strategies.impl.WalletPaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentStrategyManager {

    private final WalletPaymentStrategy walletPaymentStrategy;
    private final CashPaymentStrategy cashPaymentStrategy;

    public PaymentStrategy paymentStrategy(PaymentMethod paymentMethod) {
        return switch (paymentMethod){
            case WALLET -> walletPaymentStrategy;
            case CASH -> cashPaymentStrategy;
        };
    }

}

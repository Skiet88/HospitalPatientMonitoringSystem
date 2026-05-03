package com.hpms.patterns.factorymethod;

public class CreditCardProcessor implements PaymentProcessor {
    @Override
    public String processPayment(double amount) {
        return String.format("Credit card payment processed: %.2f", amount);
    }
}
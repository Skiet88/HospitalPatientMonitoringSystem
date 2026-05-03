package com.hpms.patterns.factorymethod;

public class PayPalProcessor implements PaymentProcessor {
    @Override
    public String processPayment(double amount) {
        return String.format("PayPal payment processed: %.2f", amount);
    }
}
package com.hpms.patterns.factorymethod;

public abstract class PaymentService {
    protected abstract PaymentProcessor createProcessor();

    public String pay(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0.");
        }
        return createProcessor().processPayment(amount);
    }
}
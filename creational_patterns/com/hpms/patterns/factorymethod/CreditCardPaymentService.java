package com.hpms.patterns.factorymethod;

public class CreditCardPaymentService extends PaymentService {
    @Override
    protected PaymentProcessor createProcessor() {
        return new CreditCardProcessor();
    }
}
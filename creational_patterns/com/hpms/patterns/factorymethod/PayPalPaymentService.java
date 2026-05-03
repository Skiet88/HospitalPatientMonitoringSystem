package com.hpms.patterns.factorymethod;

public class PayPalPaymentService extends PaymentService {
    @Override
    protected PaymentProcessor createProcessor() {
        return new PayPalProcessor();
    }
}
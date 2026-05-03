package com.hpms.patterns;

import com.hpms.patterns.factorymethod.CreditCardPaymentService;
import com.hpms.patterns.factorymethod.PayPalPaymentService;
import com.hpms.patterns.factorymethod.PaymentService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FactoryMethodTest {

    @Test
    void delegatesCreationToCreditCardService() {
        PaymentService service = new CreditCardPaymentService();
        String result = service.pay(100.0);
        assertTrue(result.contains("Credit card"));
    }

    @Test
    void delegatesCreationToPaypalService() {
        PaymentService service = new PayPalPaymentService();
        String result = service.pay(200.0);
        assertTrue(result.contains("PayPal"));
    }

    @Test
    void rejectsInvalidPaymentAmounts() {
        PaymentService service = new PayPalPaymentService();
        assertThrows(IllegalArgumentException.class, () -> service.pay(0));
    }
}
package com.revolution.payment.service.api.exception;

public class PaymentNotFoundException extends RuntimeException {

    PaymentNotFoundException(long orderId) {
        super("Payment with order id " + orderId + " not found");
    }
}

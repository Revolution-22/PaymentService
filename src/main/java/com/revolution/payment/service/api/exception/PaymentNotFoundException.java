package com.revolution.payment.service.api.exception;

public class PaymentNotFoundException extends RuntimeException {

    public PaymentNotFoundException(long orderId) {
        super("Payment with order transactionId " + orderId + " not found");
    }
}

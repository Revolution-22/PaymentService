package com.revolution.payment.service.api.request;

public record PaymentStatusRequest(
        long transactionId,
        int status
) {
}
package com.revolution.payment.service.api.request;

public record PaymentRequest(
        long orderId,
        long receiverId
) {
}

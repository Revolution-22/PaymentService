package com.revolution.payment.service.api.request;

public record PaymentStatusRequest(
        long orderId,
        long receiverId,
        int status
) {
}
package com.revolution.payment.service.api.dto;

public record PaymentDto(
        Long transactionId,
        long orderId,
        long receiverId,
        int status
) {
}

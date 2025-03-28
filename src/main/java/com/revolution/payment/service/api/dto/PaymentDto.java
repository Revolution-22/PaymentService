package com.revolution.payment.service.api.dto;

public record PaymentDto(
        Long id,
        long orderId,
        long receiverId,
        int status
) {
}

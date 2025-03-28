package com.revolution.payment.service.api.request;

import java.math.BigDecimal;

public record PaymentRequest(
        long orderId,
        BigDecimal amount,
        long receiverId
) {
}

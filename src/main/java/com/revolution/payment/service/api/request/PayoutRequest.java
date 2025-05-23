package com.revolution.payment.service.api.request;

import java.math.BigDecimal;

public record PayoutRequest(
        String bankAccountNumber,
        long orderId,
        long receiverId,
        BigDecimal amount
) {
}
